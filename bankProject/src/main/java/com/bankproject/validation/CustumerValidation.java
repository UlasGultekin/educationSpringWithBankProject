package com.bankproject.validation;

import com.bankproject.entities.Customer;
import com.bankproject.enums.REnum;
import com.bankproject.loggers.LogDbService;
import com.bankproject.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
public class CustumerValidation {
    private LogDbService log;
    private CustomerRepository customerRepository;

    public CustumerValidation() {

    }

    public ResponseEntity ResponseEntity (Customer customer){
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
        hashMap.put(REnum.status,false);
        hashMap.put(REnum.message,"User already exists");
        return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
    }

}
