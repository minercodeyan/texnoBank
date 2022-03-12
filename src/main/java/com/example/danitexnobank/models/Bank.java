package com.example.danitexnobank.models;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "texnobank")
public class Bank {
    @Id
    @Column(name = "id_texno_bank", nullable = false)
    private Long id;

    @Column(name = "fond")
    private Double fond;

    @OneToMany(mappedBy ="bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Deposit> deposits;


    public Double getFond() {
        return fond;
    }

    public void setFond(Double fond) {
        this.fond = fond;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
