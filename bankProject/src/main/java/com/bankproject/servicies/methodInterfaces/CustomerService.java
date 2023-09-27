package com.bankproject.servicies.methodInterfaces;

import com.bankproject.entities.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<?> accountAplication(Customer customer);
    ResponseEntity<?> login(String customerNo,String password);
    ResponseEntity<?> accountClosing(String customerNo,String possword,String phoneNo);


}
