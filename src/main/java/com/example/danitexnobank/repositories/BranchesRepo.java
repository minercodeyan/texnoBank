package com.example.danitexnobank.repositories;


import com.example.danitexnobank.models.Branches;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchesRepo extends CrudRepository<Branches,Long> {
}
