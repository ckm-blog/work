package com.ckm.enums;

public enum YesOrNo {

    NO(0,"否"),
    YES(1,"是");

    public Integer type;

    public String name;


    YesOrNo(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
}
