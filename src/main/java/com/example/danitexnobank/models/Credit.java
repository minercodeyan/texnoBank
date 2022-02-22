package com.example.danitexnobank.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private double sum;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User creditUser;
    private double percent;
    private boolean isConfirm;
    private Date startDate;
    private int term;

    public Credit(){


    }

    public Credit(double sum, User creditUser, double percent) {
        this.sum = sum;
        this.creditUser = creditUser;
        this.percent = percent;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
