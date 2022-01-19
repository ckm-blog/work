package com.ckm.enums;

public enum Sex {

    woman(0,"女"),
    man(1,"男"),
    secret(2,"保密");

    public Integer type;

    public String name;


    Sex(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
}
