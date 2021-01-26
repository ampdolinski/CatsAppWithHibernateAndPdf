package com.sda.cats.model;

import java.util.List;

public class CreateCatRequest {

    private String name;

    private Integer tailLength;

    private Gender gender;

    private List<CreateToyRequest> toys;

    public List<CreateToyRequest> getToys() {
        return toys;
    }

    public void setToys(List<CreateToyRequest> toys) {
        this.toys = toys;
    }

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
