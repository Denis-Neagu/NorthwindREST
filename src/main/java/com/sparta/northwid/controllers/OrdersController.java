package com.sparta.northwid.controllers;

import com.sparta.northwid.dto.OrdersDto;
import com.sparta.northwid.entities.CustomerEntity;
import com.sparta.northwid.entities.OrderEntity;
import com.sparta.northwid.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
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
    public List<OrdersDto> getOrdersByCustomer(@RequestParam String customerId) {
        List<OrdersDto> orderEntities = new ArrayList<>();

        for (OrderEntity order : ordersRepository.findAll()) {

            OrdersDto ordersDto = new OrdersDto(order);
            CustomerEntity customer = ordersDto.getCustomerID();

            if (customer.getId().equalsIgnoreCase(customerId)) {
                orderEntities.add(ordersDto);
            }
        }

        return orderEntities;
    }

    @GetMapping(value = "/orders/date", params = {"orderDate"})
    @ResponseBody
    public List<OrderEntity> getOrdersByDate(@RequestParam String orderDate) {
        List<OrderEntity> orderEntities = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        try {
            Instant instant = sdf.parse(orderDate).toInstant();

            for (OrderEntity order : ordersRepository.findAll()) {
                if(order.getOrderDate().compareTo(instant)==0) {
                    orderEntities.add(order);
                }
            }
        } catch (ParseException e)  {
            e.printStackTrace();

        }

        return orderEntities;
    }

}
