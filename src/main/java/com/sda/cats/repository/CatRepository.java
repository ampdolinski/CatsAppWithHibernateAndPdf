package com.sda.cats.repository;

import com.sda.cats.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> findCatsByName(String name);

    List<Cat> findCatsByNameAndTailLengthBetween(String name, Integer min, Integer max);

    default List<Cat> findCats(String name, Integer min, Integer max){
        return findCatsByNameAndTailLengthBetween(name,min,max);
    }
}
