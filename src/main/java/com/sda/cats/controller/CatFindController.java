package com.sda.cats.controller;

import com.sda.cats.model.Cat;
import com.sda.cats.repository.CatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cats/find")
public class CatFindController {

    //LAZY: powinniśmy wstrzykiwać serwis a nie repozytorium
    private CatRepository catRepository;

    public CatFindController(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @GetMapping("/name")
    public ResponseEntity<List<Cat>> findCats(@RequestParam("name") String catName,
                                                    @RequestParam("min") Integer min,
                                                    @RequestParam("max") Integer max){
        List<Cat> cats = catRepository.findCats(catName,min,max);
        return new ResponseEntity<>(cats, HttpStatus.OK);
    }


}
