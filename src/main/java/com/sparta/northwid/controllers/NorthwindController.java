package com.sparta.northwid.controllers;


import com.sparta.northwid.entities.CustomerEntity;
import com.sparta.northwid.entities.ProductEntity;
import com.sparta.northwid.repositories.CustomersRepository;
import com.sparta.northwid.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController //ensures that the data is returned in a JSON format
public class NorthwindController {

    private ProductsRepository productsRepository;
    private CustomersRepository customersRepository;


    @Autowired
    public NorthwindController(ProductsRepository productsRepository, CustomersRepository customersRepository) { //make a constructor for it
        this.productsRepository = productsRepository;
        this.customersRepository = customersRepository;
    }

    @GetMapping("/customers")
    @ResponseBody
    public List<CustomerEntity> getAllCustomers(@RequestParam(required = false) String name) {
        if (name == null) {
            return customersRepository.findAll();
        }

        List<CustomerEntity> foundCustomers = new ArrayList<>();
        for(CustomerEntity customerEntity : customersRepository.findAll()) {
            if (customerEntity.getContactName().contains(name)) {
                foundCustomers.add(customerEntity);
            }
        }
        return foundCustomers;
    }

    @GetMapping("/products")
    public List<ProductEntity> getAllProducts() {

        return productsRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<ProductEntity> getProductsById(@PathVariable Integer id) {

        return productsRepository.findById(id);
    }
}
