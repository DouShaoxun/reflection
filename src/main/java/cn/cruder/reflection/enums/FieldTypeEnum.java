package cn.cruder.reflection.enums;

/**
 * @Author: cruder
 * @Date: 2020-11-21 15:53
 * @Description: description
 */

public enum FieldTypeEnum {
    STRING_TYPE("StringType"),
    NUMBER_TYPE("NumberType"),
    DATE_TYPE("DateTYPE"),
    ;

    private String fieldType;

    FieldTypeEnum(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldType() {
        return fieldType;
    }
}
