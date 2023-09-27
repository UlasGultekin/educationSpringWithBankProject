package com.bankproject.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cusId;

    private String name;
    private String surName;
    private String password;
    private String customerNo;
    private String email;
    private String tcNo;
    private String ybNo;
    private String taxNo;
    private String photo;
    private String phoneNo;
    private String contactAddres;
    private String customerType;
    private String country;
    private String status;





}