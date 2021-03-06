package com.example.danitexnobank.models;

import javax.persistence.*;

@Entity
public class Branches {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String ServiceForIndividuals;
    private String serviceInfo;

    public Branches() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServiceForIndividuals() {
        return ServiceForIndividuals;
    }

    public void setServiceForIndividuals(String serviceForIndividuals) {
        ServiceForIndividuals = serviceForIndividuals;
    }

    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
    }
}

