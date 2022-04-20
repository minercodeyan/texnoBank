package com.example.danitexnobank.models;


import javax.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "currency_id", nullable = false)
    private Long id;

    private String currency;

    public Currency() {
    }

    public Currency(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
