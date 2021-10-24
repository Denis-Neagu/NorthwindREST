package com.sparta.northwid;

import com.sparta.northwid.controllers.OrdersController;
import com.sparta.northwid.entities.CustomerEntity;
import com.sparta.northwid.entities.OrderEntity;
import com.sparta.northwid.repositories.CustomersRepository;
import com.sparta.northwid.repositories.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrdersControllerTest {

    @Mock
    CustomersRepository customersRepository;
    @Mock
    OrdersRepository ordersRepository;

    @InjectMocks
    OrdersController ordersController;

    @Test
    void getCustomerByOrderReturnsCorrectCustomer() {
        CustomerEntity customerEntity = new CustomerEntity();

        List<OrderEntity> orderEntities = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(123);
        orderEntity.setCustomerID(customerEntity);

        CustomerEntity customer =  orderEntity.getCustomerID();
        customer.setId("ALFKI");

        orderEntities.add(orderEntity);

        Mockito.when(ordersRepository.findAll()).thenReturn(orderEntities);

        List<CustomerEntity> response = ordersController.getCustomerByOrder(123);

        Assertions.assertTrue(response.size() > 0);
        Assertions.assertEquals(customer, response.get(0));
    }
}
