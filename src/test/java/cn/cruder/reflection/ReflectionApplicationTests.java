package cn.cruder.reflection;

import cn.cruder.reflection.entity.ProjectResourceDataInformation;
import cn.cruder.reflection.enums.FieldTypeEnum;
import cn.cruder.reflection.enums.ProjectResourceDataInformationEnum;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Pattern;

@Slf4j
@SpringBootTest
class ReflectionApplicationTests {
    private static final String GYS = "供应商";
    private static final String CBS = "承包商";
    private static final String INDIVIDUAL_INDICATORS = "各单体指标";
    private static final String MONOSOMEINDICATOR = "monosomeIndicator";
    private static final String NATURE = "Nature";
    private static Pattern positiveIntegerPattern = Pattern.compile("^[1-9]\\d*$");
    private static Pattern pattern = Pattern.compile("^(\\-)?\\d+(\\.\\d+)?$");
    private static Pattern percentagePattern = Pattern.compile("/^(100|[1-9]?\\d(\\.\\d\\d?\\d?)?)%$|0$/");

    /**
     * 测试
     * @throws IOException
     * @throws InvalidFormatException
     */
    @Test
    void contextLoads() throws IOException, InvalidFormatException {
        System.out.println();
        String fileName = "data.xlsx";
        String filePath = getFilePath(fileName);
        File file = new File(filePath);
        InputStream in = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(in);
        Sheet sheet = workbook.getSheetAt(0);
        ProjectResourceDataInformation information = analysisDataInformation(sheet);
        if (information != null) {
            System.out.println(JSONUtil.toJsonStr(information));
        }
    }

    private ProjectResourceDataInformation analysisDataInformation(Sheet sheet) {
        if (sheet == null) {
            return null;
        }
        ProjectResourceDataInformation information = new ProjectResourceDataInformation();
        try {
            int rowCount = sheet.getLastRowNum();
            Row row = null;
            Boolean flag = true;
            LinkedList<HashMap> objects = new LinkedList<>();

            for (int i = 0; i < rowCount; i++) {
                row = sheet.getRow(i);
                String cellValue = getCellValue(row.getCell(0));
                if (StringUtils.isNotBlank(cellValue)) {
                    if (StringUtils.equals(INDIVIDUAL_INDICATORS, cellValue)) {
                        flag = false;
                        continue;
                    }
                    if (flag) {
                        //适用于 各单体指标 之前的数据
                        String excelValue = cellValue;
                        // value拼成json  XX供应商  XX不导入
                        if (cellValue.endsWith(GYS) && !cellValue.startsWith("×")) {
                            excelValue = GYS;
                        }
                        if (cellValue.endsWith(CBS) && !cellValue.startsWith("×")) {
                            excelValue = CBS;
                        }
                        ProjectResourceDataInformationEnum anEnum = ProjectResourceDataInformationEnum.getByExcelValue(excelValue);
                        if (anEnum != null) {
                            Object o = null;
                            Cell cell1 = row.getCell(1);
                            String fieldName = anEnum.getFieldName();
                            String fieldType = anEnum.getFieldType();
                            if (StringUtils.equals(fieldType, FieldTypeEnum.NUMBER_TYPE.getFieldType())) {
                                String cellValueTemp = getCellValue(cell1);
                                if (pattern.matcher(cellValueTemp).matches()
                                        || positiveIntegerPattern.matcher(cellValueTemp).matches()) {
                                    o = new BigDecimal(cellValueTemp);
                                }
                                if (percentagePattern.matcher(cellValueTemp).matches()) {
                                    o = new BigDecimal(cellValueTemp.replace("%", ""));
                                }
                            }
                            if (StringUtils.equals(fieldType, FieldTypeEnum.DATE_TYPE.getFieldType())) {
                                // 时间格式化
                                String cellValueTemp = getCellValue(cell1);
                                if (!StringUtils.equals("/", cellValueTemp) && StringUtils.isNotBlank(cellValueTemp)) {
                                    o = cn.hutool.core.date.DateUtil.parse(cellValueTemp);
                                }
                            }
                            if (StringUtils.equals(fieldType, FieldTypeEnum.STRING_TYPE.getFieldType())) {
                                o = getCellValue(cell1);
                                if (StringUtils.equals(excelValue, GYS) || StringUtils.equals(excelValue, CBS)) {
                                    HashMap<String, Object> map = new HashMap<>(1);
                                    map.put(cellValue, o.toString());
                                    o = JSONUtil.toJsonStr(map);
                                }
                            }
                            setFieldValueByFieldName(fieldName, information, o);
                            // 设置性质属性的值  性质字段 命名方式 正常字段加Nature后缀
                            if (isNatureField(fieldName)) {
                                Cell cell2 = row.getCell(2);
                                String cellValueTemp = getCellValue(cell2);
                                setFieldValueByFieldName(fieldName + NATURE, information, cellValueTemp);
                            }
                        }
                    }
                    // 各单体指标 json数组
                    // 判断条件待定
                    if (cellValue.startsWith("单体") && cellValue.endsWith("名称")) {
                        // 连续向下读取5 行 i+=4
                        HashMap<String, Object> map = new HashMap<>();
                        map.put(cellValue, getCellValue(row.getCell(1)));

                        // 单体面积
                        Row row1 = sheet.getRow(++i);
                        String rc10 = getCellValue(row1.getCell(0));
                        String rc11 = getCellValue(row1.getCell(1));
                        map.put(rc10, rc11);

                        //地上层数
                        Row row2 = sheet.getRow(++i);
                        String rc20 = getCellValue(row2.getCell(0));
                        String rc21 = getCellValue(row2.getCell(1));
                        map.put(rc20, rc21);

                        //地上面积
                        Row row3 = sheet.getRow(++i);
                        String rc30 = getCellValue(row3.getCell(0));
                        String rc31 = getCellValue(row3.getCell(1));
                        map.put(rc30, rc31);

                        //地下层数
                        Row row4 = sheet.getRow(++i);
                        String rc40 = getCellValue(row4.getCell(0));
                        String rc41 = getCellValue(row4.getCell(1));
                        map.put(rc40, rc41);

                        //地下面积
                        Row row5 = sheet.getRow(++i);
                        String rc50 = getCellValue(row5.getCell(0));
                        String rc51 = getCellValue(row5.getCell(1));
                        map.put(rc50, rc51);
                        objects.addLast(map);

                    }
                }
            }
            // 存储 各单体指标
            setFieldValueByFieldName(MONOSOMEINDICATOR, information, JSONUtil.toJsonStr(objects));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return information;
    }


    /**
     * 判断该字段是否需要读取备注列
     *
     * @param fileName
     * @return
     */
    private Boolean isNatureField(String fileName) {
        if (StringUtils.equals(fileName, ProjectResourceDataInformationEnum.CONSTRUCTION.getFieldName())
                || StringUtils.equals(fileName, ProjectResourceDataInformationEnum.DESIGNUNIT.getFieldName())
                || StringUtils.equals(fileName, ProjectResourceDataInformationEnum.SURVEYUNIT.getFieldName())
                || StringUtils.equals(fileName, ProjectResourceDataInformationEnum.BIDDINGAGENCY.getFieldName())
                || StringUtils.equals(fileName, ProjectResourceDataInformationEnum.COSTCONSULTATION.getFieldName())
                || StringUtils.equals(fileName, ProjectResourceDataInformationEnum.MAINCONTRACTOR.getFieldName())
                || StringUtils.equals(fileName, ProjectResourceDataInformationEnum.SUPERVISUNIT.getFieldName())
        ) {
            return true;
        }
        return false;
    }

    /**
     * 反射 根据属性名设置属性值
     *
     * @param fieldName 字段名称
     * @param object    对象
     * @param value     字段值
     */
    private void setFieldValueByFieldName(String fieldName, Object object, Object value) {
        try {
            // 获取obj类的字节文件对象
            Class c = object.getClass();
            // 获取该类的成员变量
            Field f = c.getDeclaredField(fieldName);
            // 取消语言访问检查
            f.setAccessible(true);
            // 给变量赋值
            f.set(object, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 获取excel表格中的数据
     *
     * @param cell
     * @return
     */
    protected String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        StringBuffer cellValue = null;
        if (CellType.NUMERIC.getCode() == cell.getCellType()) {
            cellValue = new StringBuffer(cell.getNumericCellValue() + "");
        } else if (CellType.STRING.getCode() == cell.getCellType()) {
            cellValue = new StringBuffer(cell.getStringCellValue());
        }
        return cellValue == null ? "" : cellValue.toString();
    }

    /**
     * 获取Excel文件路径
     * @param fileName
     * @return
     * @throws IOException
     */
    private String getFilePath(String fileName) throws IOException {
        StringBuilder resourcesPath = new StringBuilder("");
        resourcesPath.append("src").append(File.separator).append("main").append(File.separator).append("resources");
        File directory = new File(resourcesPath.toString());
        return directory.getCanonicalPath() + File.separator + "templates" + File.separator + "excel" + File.separator + fileName;
    }

}
