package com.sda.cats.model;

import javax.persistence.*;
import java.util.List;

// name = "???" -> nazwa tabeli
@Entity(name = "cats")
public class Cat {

    public Cat() {
    }

    public Cat(String name, Integer tailLength, Gender gender) {
        this.name = name;
        this.tailLength = tailLength;
        this.gender = gender;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer tailLength;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cat_id")
    private List<Toy> toys;

    public List<Toy> getToys() {
        return toys;
    }

    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getTailLength() {
        return tailLength;
    }

    public Gender getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTailLength(Integer tailLength) {
        this.tailLength = tailLength;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
