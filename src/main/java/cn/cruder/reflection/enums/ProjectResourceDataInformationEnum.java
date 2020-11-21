package cn.cruder.reflection.enums;

/**
 * @Author: cruder
 * @Date: 2020-11-21 15:52
 * @Description: description
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

/**
 * 枚举
 *
 * @author DouShaoxun
 */
public enum ProjectResourceDataInformationEnum {
    PROJECTNAME("工程名称", "projectName"),
    PROJECTNUMBER("项目编号", "projectNumber"),
    CONSTRUCTION("建设单位", "construction"),
    DESIGNUNIT("设计单位", "designUnit"),
    SURVEYUNIT("勘察单位", "surveyUnit"),
    BIDDINGAGENCY("招标代理", "biddingAgency"),
    COSTCONSULTATION("造价咨询", "costConsultation"),
    MAINCONTRACTOR("总包单位", "mainContractor"),
    SUPERVISUNIT("监理单位", "supervisUnit"),
    CONSTRUCTIONSITE("建设地点", "constructionSite"),
    NATUREOFLAND("土地性质", "natureOfLand"),
    CAPITALSOURCE("资金来源", "capitalSource"),
    CONTRACTWAY("发包方式", "contractWay"),
    FORMOFTREATY("合同方式", "formOfTreaty"),
    PROJECTINVESTMENT("项目投资", "projectInvestment", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    CIVILANDERECTIONCOST("建安工程费", "civilAndErectionCost", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    FLOORSPACE("占地面积", "floorSpace", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    TOTALFLOORAREA("总建筑面积", "totalFloorArea", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    GROUNDAREA("地上面积", "groundArea", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    UNDERGROUNDAREA("地下面积", "undergroundArea", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    HIGHBUILDINGEAVES("建筑檐高", "highBuildingEaves", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    FOUNDATIONTREATMENT("地基处理", "foundationTreatment"),
    DEPTH("基础埋深", "depth", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    BASETYPE("基础类型", "baseType"),
    UNDERGROUNDWATERLEVEL("地下水位", "undergroundWaterLevel", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    FOUNDATIONPIT("基坑支护", "foundationPit"),
    STRUCTURETYPE("结构类型", "structureType"),
    USEFUNCTION("使用功能", "useFunction"),
    DEFENSIVEINTENSITY("设防烈度", "defensiveIntensity"),
    ENERGYSAVINGSTANDARD("节能标准", "energySavingStandard", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    QUALITYSTANDARD("质量标准", "qualityStandard"),
    QUALITYAWARD("质量奖项", "qualityAward"),
    CIVILAIRDEFENSEAREA("人防面积", "civilAirDefenseArea", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    CIVILAIRDEFENSETYPE("人防类型", "civilAirDefenseType"),
    NORMALUSE("平常用途", "normalUse"),
    WARTIMEPURPOSES("战时用途", "wartimePurposes"),
    PLANCONSTRUCTION("计划开工", "planConstruction", FieldTypeEnum.DATE_TYPE.getFieldType()),
    PLANCOMPLETION("计划竣工", "planCompletion", FieldTypeEnum.DATE_TYPE.getFieldType()),
    PLANDURATION("计划工期", "planDuration", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    ACTUALCONSTRUCTION("实际开工", "actualConstruction", FieldTypeEnum.DATE_TYPE.getFieldType()),
    ACTUALCOMPLETION("实际竣工", "actualCompletion", FieldTypeEnum.DATE_TYPE.getFieldType()),
    ACTUALDURATION("实际工期", "actualDuration", FieldTypeEnum.NUMBER_TYPE.getFieldType()),
    EXTERIORWALLDECORATION("外墙装修", "exteriorWallDecoration"),
    INTERIORDECORATIONSCOPE("内装范围", "interiorDecorationScope"),
    INTERIORDECORATIONSTANDARD("内装标准", "interiorDecorationStandard"),
    DOORSANDWINDOWS("门窗做法", "doorsAndWindows"),
    HEATINGMODE("采暖方式", "heatingMode"),
    REFRIGERATIONMODE("制冷方式", "refrigerationMode"),
    SUPPLIERS("供应商", "suppliers"),
    CONTRACTOR("承包商", "contractor"),

    ;

    private String excelValue;
    private String fieldName;
    private String fieldType;


    ProjectResourceDataInformationEnum(String excelValue, String fieldName) {
        this.excelValue = excelValue;
        this.fieldName = fieldName;
        this.fieldType = FieldTypeEnum.STRING_TYPE.getFieldType();
    }

    ProjectResourceDataInformationEnum(String excelValue, String fieldName, String fieldType) {
        this.excelValue = excelValue;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public String getExcelValue() {
        return excelValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    @Nullable
    public static ProjectResourceDataInformationEnum getByExcelValue(String excelValue) {
        for (ProjectResourceDataInformationEnum at : ProjectResourceDataInformationEnum.values()) {
            if (StringUtils.equals(at.getExcelValue(), excelValue)) {
                return at;
            }
        }
        return null;
    }
}
