package com.sparta.northwid;

import com.sparta.northwid.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomersControllerTest {

    @Autowired
    CustomersRepository customersRepository;


}
