package com.sda.cats.service;

import com.sda.cats.exception.CatNotFoundException;
import com.sda.cats.model.Cat;
import com.sda.cats.model.CreateCatRequest;
import com.sda.cats.model.Gender;
import com.sda.cats.model.UpdateCatRequest;
import com.sda.cats.repository.CatRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatManager implements InitializingBean {

    private CatRepository catRepository;

    public CatManager(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public List<Cat> getCats() {
        return catRepository.findAll();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        saveCat();
    }

    private void saveCat() {
        Cat cat = new Cat("Rys", 666, Gender.FEMALE);
        catRepository.save(cat);
    }

    public void saveCat(CreateCatRequest catRequest) {
        //java 11
        var entity = CatMapper.map(catRequest);
        //java 8
        //Cat entity = CatMapper.map(catRequest);
        catRepository.save(entity);
    }


    public void delete(Long id) {
        catRepository.findById(id)
                .orElseThrow(CatNotFoundException::new);
                //.orElseThrow(() -> new CatNotFoundException());
        catRepository.deleteById(id);
    }

    public void updateCat(Long id, UpdateCatRequest updateCatRequest) {
        var cat = catRepository.findById(id)
                .orElseThrow(CatNotFoundException::new);
        cat.setName(updateCatRequest.getName());
        cat.setTailLength(updateCatRequest.getTailLength());
        cat.setGender(updateCatRequest.getGender());
        catRepository.save(cat);
    }

    public void patchCat(Long id, UpdateCatRequest updateCatRequest) {
        var cat = catRepository.findById(id)
                .orElseThrow(CatNotFoundException::new);
        //java 7
//        if(updateCatRequest.getName() != null) {
//            cat.setName(updateCatRequest.getName());
//        }
//        if(updateCatRequest.getTailLength() != null) {
//            cat.setTailLength(updateCatRequest.getTailLength());
//        }
//        if(updateCatRequest.getGender() != null) {
//            cat.setGender(updateCatRequest.getGender());
//        }
        //java 8
        //Optional.ofNullable(updateCatRequest.getName()).ifPresent(i -> cat.setName(i));
        Optional.ofNullable(updateCatRequest.getName()).ifPresent(cat::setName);
        Optional.ofNullable(updateCatRequest.getGender()).ifPresent(cat::setGender);
        Optional.ofNullable(updateCatRequest.getTailLength()).ifPresent(cat::setTailLength);

        catRepository.save(cat);
    }
}
