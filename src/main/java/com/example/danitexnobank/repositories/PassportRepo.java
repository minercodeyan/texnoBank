package com.example.danitexnobank.repositories;


import com.example.danitexnobank.models.PassportInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepo extends CrudRepository<PassportInfo,Long> {
}
