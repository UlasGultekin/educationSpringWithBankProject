package com.bankproject.controllers;


import com.bankproject.entities.Customer;
import com.bankproject.enums.REnum;
import com.bankproject.loggers.LogDbService;
import com.bankproject.servicies.methodInterfaces.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bankproject.enums.REnum.message;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
   private CustomerService customerService;
   private LogDbService log;

    @PostMapping("/save")
    public ResponseEntity<?> accountAplication(@RequestBody Customer customer){
        log.saveLog("Müşteri numarası"+customer.getName()+" olan ve Tc no "+customer.getTcNo()+
                " olan verielri ile istek alınmıştır");
        return customerService.accountAplication(customer);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String customerNo,@RequestParam String password){
       return customerService.login(customerNo,password);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> accountClosing(@RequestParam String customerNo,@RequestParam String password,@RequestParam String phoneNo){
       return customerService.accountClosing(customerNo,password,phoneNo);
    }



}
