package com.example.danitexnobank.repositories;
import com.example.danitexnobank.models.Credit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRepository extends CrudRepository<Credit, Long> {
    Optional<Credit> findById(Long id);
}
