package com.example.danitexnobank.repositories;


import com.example.danitexnobank.models.ClientInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends CrudRepository<ClientInfo,Long> {
}
