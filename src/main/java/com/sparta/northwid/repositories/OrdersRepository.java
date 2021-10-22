package com.sparta.northwid.repositories;

import com.sparta.northwid.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrderEntity, Integer> {
}