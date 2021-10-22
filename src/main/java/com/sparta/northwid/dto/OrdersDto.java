package com.sparta.northwid.dto;

import com.sparta.northwid.entities.CustomerEntity;
import com.sparta.northwid.entities.OrderEntity;

public class OrdersDto {

    private int orderId;
    private CustomerEntity customerID;

    public OrdersDto(OrderEntity orderEntity) {
        this.orderId = orderEntity.getId();
        this.customerID = orderEntity.getCustomerID();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public CustomerEntity getCustomerID() {
        return customerID;
    }

    public void setCustomerID(CustomerEntity customerID) {
        this.customerID = customerID;
    }
}
