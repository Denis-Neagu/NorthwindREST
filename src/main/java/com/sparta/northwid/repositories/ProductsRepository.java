package com.sparta.northwid.repositories;

import com.sparta.northwid.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, Integer> {
}