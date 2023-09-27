package com.bankproject.repositories;

import com.bankproject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByTcNo(String tcNo);

    List<Customer> findByYbNo(String ybNo);

    List<Customer> findByTaxNo(String taxNo);

    Optional<Customer> findByCustomerNoAndPassword(String customerNo, String password);
    Optional<Customer> findByCustomerNoAndPasswordAndPhoneNo(String customerNo,String password,String phoneNo);

    @Transactional
    long deleteByCustomerNo(String customerNo);








}