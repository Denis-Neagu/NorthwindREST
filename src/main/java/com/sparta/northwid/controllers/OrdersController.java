package com.sparta.northwid.controllers;

import com.sparta.northwid.entities.CustomerEntity;
import com.sparta.northwid.entities.Employee;
import com.sparta.northwid.entities.OrderEntity;
import com.sparta.northwid.repositories.CustomersRepository;
import com.sparta.northwid.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrdersController {
    private OrdersRepository ordersRepository;

    @Autowired
    public OrdersController(OrdersRepository ordersRepository) {
        this.ordersRepository=ordersRepository;
    }

    @GetMapping("/orders/{id}")
    public Optional<OrderEntity> getOrdersById(@PathVariable Integer id) {
        return ordersRepository.findById(id);
    }


    @GetMapping(value = "/orders/customer", params = {"orderId"} )
    @ResponseBody
    public List<CustomerEntity> getCustomerByOrder(@RequestParam Integer orderId) {
        Map<Integer, CustomerEntity> map = new HashMap<>();

        for(OrderEntity order : ordersRepository.findAll()) {
            if (order.getId().equals(orderId)) {
                map.put(orderId, order.getCustomerID());
            }
        }

        return new ArrayList<>(map.values());
    }

    @GetMapping(value = "/orders", params = {"customerId"})
    @ResponseBody
    public List<OrderEntity> getOrdersByCustomer(@RequestParam String customerId) {
        List<OrderEntity> orderEntities = new ArrayList<>();

        for (OrderEntity order : ordersRepository.findAll()) {
            CustomerEntity customer = order.getCustomerID();

            if (customer.getId().equalsIgnoreCase(customerId)) {
                orderEntities.add(order);
            }
        }

        return orderEntities;
    }
}
