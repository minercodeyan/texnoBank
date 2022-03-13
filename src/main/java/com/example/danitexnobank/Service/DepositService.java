package com.example.danitexnobank.Service;


import com.example.danitexnobank.models.Bank;
import com.example.danitexnobank.models.Deposit;
import com.example.danitexnobank.models.User;
import com.example.danitexnobank.repositories.BankRepo;
import com.example.danitexnobank.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private BankRepo bankRepo;
    @Autowired
    private BankService bankService;
    @Autowired
    private TimerService timerService;

    public void takeDeposit(long id) {
        Optional<Deposit> optionalDeposit = depositRepository.findById(id);
        Deposit deposit = optionalDeposit.get();
        bankService.reduseFond(deposit);
        depositRepository.delete(deposit);
    }

    public void confirmDeposit(long id) {
        Optional<Deposit> optionalDeposit = depositRepository.findById(id);
        Deposit deposit = optionalDeposit.get();
        deposit.setConfirm(true);
        Optional<Bank> optionalBank = bankRepo.findById(1L);
        Bank bank = optionalBank.get();
        bank.setFond(bankService.increaseFond(bank.getFond(), deposit));
        bankRepo.save(bank);
        depositRepository.save(deposit);
    }

    public void addDeposit(User user, Deposit deposit) {
        deposit.setCreditUser(user);
        if (deposit.getType().equals("srochniy")) {
            deposit.setEndDate(timerService.endDateMethod(deposit.getTerm()));
            if (deposit.getTerm() == 3 || deposit.getTerm() == 6)
                deposit.setPercent(5);
            else
                deposit.setPercent(6);
        } else {
            deposit.setTerm(-1);
            deposit.setPercent(0.1);
        }
        depositRepository.save(deposit);
    }

    public List<Deposit> findNonConfirmetDeposits() {
        Iterable<Deposit> deposits = depositRepository.findAll();
        List<Deposit> confirmetDeposits = StreamSupport.stream(deposits.spliterator(), false).
                filter(deposit -> deposit.isConfirm() == false)
                .collect(Collectors.toList());
        return confirmetDeposits;
    }
}
