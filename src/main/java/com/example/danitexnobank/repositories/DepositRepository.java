package com.example.danitexnobank.repositories;
import com.example.danitexnobank.models.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {
    Optional<Deposit> findById(Long id);
}
