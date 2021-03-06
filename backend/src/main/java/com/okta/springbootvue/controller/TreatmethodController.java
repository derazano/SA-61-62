package com.okta.springbootvue.controller;
import com.okta.springbootvue.repository.TreatmethodRepository;
import com.okta.springbootvue.entity.Treatmethod;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;





@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TreatmethodController {

    @Autowired
    private final TreatmethodRepository treatmethodRepository;

    public  TreatmethodController(TreatmethodRepository  treatmethodRepository) {
        this.treatmethodRepository = treatmethodRepository;
    }

    @GetMapping("/treatmethod")
    public Collection<Treatmethod> Treatmethods() {
        return treatmethodRepository.findAll().stream().collect(Collectors.toList());
    }
}