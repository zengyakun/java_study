package com.example.demo;
/**
 *@ClassName Season
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2020/1/12 9:46
 *@Version 1.0
 **/
public enum Season {

    SPRING("春天", "趁春踏青"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "围炉赏雪");

    private final String name;
    private final String desc;

    Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
