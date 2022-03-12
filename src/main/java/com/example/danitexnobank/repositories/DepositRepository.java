package com.example.danitexnobank.repositories;
import com.example.danitexnobank.models.Deposit;
import com.example.danitexnobank.models.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {
    @NotNull
    Optional<Deposit> findById(Long id);
    List<Deposit> findAllByCreditUser(User user);
}
