package com.sda.cats.service;

import com.sda.cats.model.Cat;
import com.sda.cats.model.CreateCatRequest;
import com.sda.cats.model.CreateToyRequest;
import com.sda.cats.model.Toy;

import java.util.stream.Collectors;

public class CatMapper {

    public static Cat map(CreateCatRequest catRequest) {
        Cat cat = new Cat();
        cat.setName(catRequest.getName());
        cat.setGender(catRequest.getGender());
        cat.setTailLength(catRequest.getTailLength());
        cat.setToys(catRequest.getToys()
                .stream()
                //.map(i -> mapToy(i))
                .map(CatMapper::mapToy)
                .collect(Collectors.toList())
        );
        return cat;
    }

    private static Toy mapToy(CreateToyRequest toyRequest) {
        return new Toy(toyRequest.getName());
    }
}
