package cn.ac.iscas.gz.enums;

public enum  ResultTypeEnum {
    succeess("0"),fail("-1");
    private final String code;
    ResultTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
