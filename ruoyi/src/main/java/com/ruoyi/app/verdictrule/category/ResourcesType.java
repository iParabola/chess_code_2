package com.ruoyi.app.verdictrule.category;

public enum  ResourcesType {
    DICT("字典选项","01"),
    INPUT("人工填写","02"),
    ATTR("设备属性","03"),
    RULE("裁决规则","04"),
    FORMULA("计算公式","05"),
    OPERATOR("算子","06");
    private String dictName;
    private String dictValue;

    private  ResourcesType(String dictName,String dictValue){
       this.dictName = dictName;
       this.dictValue = dictValue;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
}
