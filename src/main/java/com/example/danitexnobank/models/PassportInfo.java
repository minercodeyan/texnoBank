package com.example.danitexnobank.models;


import org.hibernate.validator.constraints.Length;


import javax.persistence.*;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

@Entity
@Table(name = "PassInfo")
public class PassportInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pass_id", nullable = false)
    private Long id;


    @Pattern(regexp = "[a-zA-Z]{2}",message = "неккректный ввод")
    private String passportSeries;
    @NotEmpty(message = "не может быть пустым")
    @Length(max = 30,message = "неккоректные данные")
    private String passportNumber;
    @NotEmpty(message = "не может быть пустым")
    private String issuedBy;
    @NotEmpty(message = "не может быть пустым")
    private String issuedWhen;

    public PassportInfo(){

    }

    public PassportInfo(String passportSeries, String passportNumber, String issuedBy, String issuedWhen) {
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.issuedBy = issuedBy;
        this.issuedWhen = issuedWhen;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getIssuedWhen() {
        return issuedWhen;
    }

    public void setIssuedWhen(String issuedWhen) {
        this.issuedWhen = issuedWhen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
