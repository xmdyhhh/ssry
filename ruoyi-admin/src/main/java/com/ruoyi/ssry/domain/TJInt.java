package com.ruoyi.ssry.domain;

public class TJInt {

    private String name;
    private Integer value;

    public TJInt() {
    }

    public TJInt(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
