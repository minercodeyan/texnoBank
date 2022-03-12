package com.example.danitexnobank.Service;


import com.example.danitexnobank.models.Deposit;
import org.springframework.stereotype.Service;

@Service
public class BankService {


    public Double increaseFond(double currFond, Deposit deposit){
        currFond=currFond+(deposit.getSum());
        return currFond;
    }




}
