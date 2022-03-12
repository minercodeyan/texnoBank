package com.example.danitexnobank.repositories;

import com.example.danitexnobank.models.Bank;
import org.springframework.data.repository.CrudRepository;

public interface BankRepo extends CrudRepository<Bank,Long> {
}
