package com.example.danitexnobank.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Clients")
public class ClientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", nullable = false)
    private Long clientId;
    private String fio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "не может быть пустым")
    private Date busDate;
    @NotNull(message = "не может быть пустым")
    private String sex;
    @NotEmpty(message = "не может быть пустым")
    @Size(max = 30,message = "слишком много символов")
    private String city;
    private String isMilitary;

    public ClientInfo(){}


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsMilitary() {
        return isMilitary;
    }

    public void setIsMilitary(String isMilitary) {
        this.isMilitary = isMilitary;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getBusDate() {
        return busDate;
    }

    public void setBusDate(Date busDate) {
        this.busDate = busDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

}
