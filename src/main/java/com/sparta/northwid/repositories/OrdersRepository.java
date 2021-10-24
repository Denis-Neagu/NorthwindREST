package com.sparta.northwid.repositories;

import com.sparta.northwid.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface OrdersRepository extends JpaRepository<OrderEntity, Integer> {
}