package com.bankproject.servicies.impl;

import com.bankproject.entities.Customer;
import com.bankproject.enums.REnum;
import com.bankproject.loggers.LogDbService;
import com.bankproject.repositories.CustomerRepository;
import com.bankproject.servicies.methodInterfaces.CustomerService;
import com.bankproject.validation.CustumerValidation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private LogDbService log;


    @Override
    public ResponseEntity<?> accountAplication(Customer customer) {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
        List<Customer> hasCustomerTc = customerRepository.findByTcNo(customer.getTcNo());
        List<Customer> hasCustomerYb = customerRepository.findByYbNo(customer.getYbNo());
        List<Customer> hasCustomerTax = customerRepository.findByTaxNo(customer.getTaxNo());
        CustumerValidation custumerValidation = new CustumerValidation();

        try {
                if (!(hasCustomerTc.size()==0) && !(customer.getTcNo().isEmpty() || customer.getTcNo()==null)){
                    log.saveLog("Müşteri adı "+customer.getName()+" olan ve Tc no "+customer.getTcNo()+
                            " olan verielri mevcut olduğundan kayıt yapılamadı");
                   return custumerValidation.ResponseEntity(customer);
                }
                if (!(hasCustomerYb.size()==0) && !(customer.getYbNo().isEmpty() || customer.getYbNo()==null)){

                    log.saveLog("Müşteri adı "+customer.getName()+" olan ve Yb no "+customer.getYbNo()+
                            " olan verielri mevcut olduğundan kayıt yapılamadı");
                    return custumerValidation.ResponseEntity(customer);
                }
                if (!(hasCustomerTax.size()==0) && !(customer.getTaxNo().isEmpty() || customer.getTaxNo()==null)){
                    log.saveLog("Müşteri adı "+customer.getName()+" olan ve Tax no "+customer.getTaxNo()+
                            " olan verielri mevcut olduğundan kayıt yapılamadı");
                    return custumerValidation.ResponseEntity(customer);
                } else {
                customer.setPassword(Base64.getEncoder().encodeToString(customer.getPassword().getBytes()));
                customerRepository.save(customer);
                hashMap.put(REnum.status,true);
                hashMap.put(REnum.result,customer);
                log.saveLog("Müşteri adı "+customer.getName()+" olan ve Tc no "+customer.getTcNo()+
                        " olan veriler kayıt edilmiştir");
                return new ResponseEntity<>(hashMap,HttpStatus.OK);
            }
        }catch (Exception e){
            hashMap.put(REnum.status,false);
            hashMap.put(REnum.result,e.getMessage());
            return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);


        }


       
    }

    @Override
    public ResponseEntity<?> login(String customerNo, String password) {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
        Optional<Customer> customerOptional=customerRepository.findByCustomerNoAndPassword(customerNo,
               Base64.getEncoder().encodeToString(password.getBytes()));
        if (customerOptional.isPresent()){
            hashMap.put(REnum.status,true);
            hashMap.put(REnum.result,"LOGIN OK");
            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }else {
            hashMap.put(REnum.status,false);
            hashMap.put(REnum.result,"CUSTOMER NO OR PASSWORD INCORRECT");
            return new ResponseEntity<>(hashMap,HttpStatus.NOT_FOUND);
        }


    }

    @Override
    public ResponseEntity<?> accountClosing(String customerNo, String password, String phoneNo) {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
        Optional<Customer> optionalCustomer=customerRepository.
                findByCustomerNoAndPasswordAndPhoneNo(customerNo,
                        Base64.getEncoder().encodeToString(password.getBytes()),phoneNo);
        if (optionalCustomer.isPresent()){
            customerRepository.deleteByCustomerNo(customerNo);
            hashMap.put(REnum.status,true);
            hashMap.put(REnum.result,"Delete Ok");
            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }
        hashMap.put(REnum.status,false);
        hashMap.put(REnum.result,"Not Delete");
        return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);

    }
}
