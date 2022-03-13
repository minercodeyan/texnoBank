package com.example.danitexnobank.Service;


import com.example.danitexnobank.models.Bank;
import com.example.danitexnobank.models.Deposit;
import com.example.danitexnobank.repositories.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankRepo bankRepo;


    public Double increaseFond(double currFond, Deposit deposit) {
        currFond = currFond + (deposit.getSum());
        return currFond;
    }

    public void reduseFond(Deposit deposit) {
        Optional<Bank> optionalBank = bankRepo.findById(1L);
        Bank bank = optionalBank.get();
        double currFond=bank.getFond()-(deposit.getSum()*deposit.getPercent());
        bank.setFond(currFond);
        bankRepo.save(bank);
    }


}
