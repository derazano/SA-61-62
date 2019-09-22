package com.okta.springbootvue.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ClaimController {
    @Autowired
    private final ClaimRepository claimRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private PaychecksRepository paychecksRepository;
    @Autowired
    private TreatmethodRepository treatmethodRepository;
    @Autowired
    private CurebyRepository curebyRepository;
    
    
        ClaimController(ClaimRepository claimRepository){
            this.claimRepository = claimRepository;
        }


        @GetMapping("/Claim")
        public Collection<Claim> Claims(){
            return claimRepository.findAll().stream().collect(Collectors.toList());
        }
        @PostMapping("/claim/{hospital_id}/{insurance_id}/{customer_id}/{treatmethod_id}/{paychecks_id}/{cureby_id}")
            public Claim newClaim(Claim newClaim,
            @PathVariable long hospital_id,
            @PathVariable long insurance_id,
            @PathVariable long customer_id,
            @PathVariable long cureby_id,
            @PathVariable long treatmethod_id,
            @PathVariable long paychecks_id){
            
            Hospital hospital = hospitalRepository.findById(hospital_id);
            Insurance insurance = insuranceRepository.findById(insurance_id);
            Customer createdBy = customerRepository.findById(customer_id);
            Cureby cureby = curebyRepository.findById(cureby_id);
            Treatmethod treatmethod = treatmethodRepository.findById(treatmethod_id);
            Paychecks paychecks = paychecksRepository.findById(paychecks_id);

            newClaim.setCreatedBy(createdBy);
            newClaim.setHospital(hospital);
            newClaim.setClaimDate(new Date());
            newClaim.setCureby(cureby);
            newClaim.setInsurance(insurance);
            newClaim.setTreatmethod(treatmethod);
            newClaim.setPaychecks(paychecks);


                return claimRepository.save(newClaim);
            }
}
