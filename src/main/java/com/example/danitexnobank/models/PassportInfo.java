package com.example.danitexnobank.models;


import javax.persistence.*;

@Entity
@Table(name = "PassInfo")
public class PassportInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pass_id", nullable = false)
    private Long id;

    private String passportSeries;
    private String passportNumber;
    private String issuedBy;
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
