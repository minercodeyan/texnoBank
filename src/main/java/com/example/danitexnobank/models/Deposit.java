package com.example.danitexnobank.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deposit")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private double sum;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User creditUser;
    private double percent;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
    private boolean isConfirm;
    private Date endDate;
    private int term;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_texno_bank")
    private Bank bank;

    public Deposit(){
    }

    public Deposit(double sum, User creditUser, double percent) {
        this.sum = sum;
        this.creditUser = creditUser;
        this.percent = percent;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public User getCreditUser() {
        return creditUser;
    }

    public void setCreditUser(User creditUser) {
        this.creditUser = creditUser;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public boolean isConfirm() {
        return isConfirm;
    }

    public void setConfirm(boolean confirm) {
        isConfirm = confirm;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

}
