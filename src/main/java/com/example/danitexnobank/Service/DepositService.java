package com.example.danitexnobank.Service;


import com.example.danitexnobank.models.Bank;
import com.example.danitexnobank.models.Deposit;
import com.example.danitexnobank.repositories.BankRepo;
import com.example.danitexnobank.repositories.DepositRepository;
import com.example.danitexnobank.repositories.UserRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
    private UserRepo userRepo;
    @Autowired
    private TimerService timerService;

    public double takeDeposit(long id) {
        Optional<Deposit> optionalDeposit = depositRepository.findById(id);
        Deposit deposit = optionalDeposit.get();
        if(deposit.getType().equals("srochniy")){
            increaseSrDeposit(id);
            inD(id);
        }
        else if(deposit.getType().equals("bessrochniy")){
            inD(id);
        }
        bankService.reduseFond(deposit);
        depositRepository.delete(deposit);
        return deposit.getSum();
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

    public void addDeposit(Deposit deposit,String name) {
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
        deposit.setCreditUser(userRepo.findByUsername(name));
        System.out.println(deposit);
        depositRepository.save(deposit);
    }

    public List<Deposit> findNonConfirmetDeposits() {
        Iterable<Deposit> deposits = depositRepository.findAll();
        List<Deposit> confirmetDeposits = StreamSupport.stream(deposits.spliterator(), false).
                filter(deposit -> !deposit.isConfirm())
                .collect(Collectors.toList());
        return confirmetDeposits;
    }


    public void increaseSrDeposit(Long id){
        Deposit deposit =  depositRepository.findById(id).orElse(null);
        LocalDate start= Instant.ofEpochMilli(deposit.getEndDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate end=LocalDate.now();
        Period period = Period.between(start, end);
        if(period.getYears()*12+period.getMonths()>=deposit.getTerm()){
            deposit.setSum(deposit.getSum()+(deposit.getSum()*deposit.getPercent()
                    *(deposit.getTerm()*30.5)/365)/100);
            System.out.println(deposit.getSum());
        }
        deposit.setType("bessrohniy");
        deposit.setTerm(-1);
        deposit.setPercent(0.1);
        depositRepository.save(deposit);
    }

    public void inD(Long id){
        Deposit deposit =  depositRepository.findById(id).orElse(null);
        LocalDate start= Instant.ofEpochMilli(deposit.getEndDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate end=LocalDate.now();
        Period period = Period.between(start, end);
        if(period.getMonths()+period.getYears()==0){
            System.out.println(deposit);
        }
        else{
            for(int i=1; i<(period.getMonths()+period.getYears()*12-deposit.getTerm());i++){
                deposit.setSum(deposit.getSum()+((deposit.getSum()*deposit.getPercent()*30.5/365)/100));
            }
        }
        depositRepository.save(deposit);
    }
}
