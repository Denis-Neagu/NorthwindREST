package com.sparta.northwid;

import com.sparta.northwid.controllers.CustomersController;
import com.sparta.northwid.entities.CustomerEntity;
import com.sparta.northwid.repositories.CustomersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class CustomersControllerTest {

    @Mock
    CustomersRepository customersRepository;
    @InjectMocks
    CustomersController customersController;

    @Test
    void getCustomersByCompanyReturnsOnlyIfClintInputIsCorrect() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCompanyName("ABC");
        customerEntity.setCity("London");

        List<CustomerEntity> entities = new ArrayList<>();
        entities.add(customerEntity);

        Mockito.when(customersRepository.findAll()).thenReturn(entities); //stub //dummy data

        ResponseEntity<List<CustomerEntity>> responseEntity = customersController.getCustomersByCompany("ABC");

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertTrue( ((List<CustomerEntity>)responseEntity.getBody()).size() > 0);

    }
}
