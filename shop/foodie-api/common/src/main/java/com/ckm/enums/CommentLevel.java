package com.ckm.enums;

public enum  CommentLevel {
    GOOD(0,"好评"),
    NORMAL(1,"中评"),
    BAD(2,"差评");

    public final Integer type;

    public final String name;


    CommentLevel(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
}
