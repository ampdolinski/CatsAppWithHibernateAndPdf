package com.sda.cats.controller;

import com.sda.cats.model.Cat;
import com.sda.cats.model.CreateCatRequest;
import com.sda.cats.model.UpdateCatRequest;
import com.sda.cats.service.CatManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {

    private CatManager catManager;

    public CatController(CatManager catManager) {
        this.catManager = catManager;
    }

    @GetMapping
    public ResponseEntity<List<Cat>> getCats(){
        List<Cat> cats = catManager.getCats();
        return new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createCat(@RequestBody CreateCatRequest catRequest){
        catManager.saveCat(catRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCat(@PathVariable("id") Long id){
        catManager.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCat(@PathVariable("id") Long id,
                                    @RequestBody UpdateCatRequest updateCatRequest){
        catManager.updateCat(id,updateCatRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchCat(@PathVariable("id") Long id,
                                    @RequestBody UpdateCatRequest updateCatRequest){
        catManager.patchCat(id,updateCatRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
}
