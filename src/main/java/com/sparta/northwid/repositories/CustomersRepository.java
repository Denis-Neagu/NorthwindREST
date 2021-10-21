package com.sparta.northwid.repositories;

import com.sparta.northwid.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<CustomerEntity, String> {
}