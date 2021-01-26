package com.sda.cats.model;

public class UpdateCatRequest {

    private String name;

    private Integer tailLength;

    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTailLength() {
        return tailLength;
    }

    public void setTailLength(Integer tailLength) {
        this.tailLength = tailLength;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
