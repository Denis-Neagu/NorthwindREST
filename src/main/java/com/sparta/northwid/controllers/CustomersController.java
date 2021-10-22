package com.sparta.northwid.controllers;

import com.sparta.northwid.entities.CustomerEntity;
import com.sparta.northwid.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CustomersController {

    CustomersRepository customerRepository;

    @Autowired
    public CustomersController(CustomersRepository customersRepository) {
        this.customerRepository = customersRepository;
    }

    //customers/company?companyName=IT
    @GetMapping(value = "/customers/company", params = {"companyName"})
    @ResponseBody
    public ResponseEntity<List<CustomerEntity>> getCustomersByCompany(@RequestParam String companyName) {
        if (companyName.matches("[a-zA-Z]+")) {
            return ResponseEntity.of(Optional.of(customerRepository.findAll()
                    .stream()
                    .filter(customerEntity -> customerEntity.getCompanyName().contains(companyName))
                    .collect(Collectors.toList())));
        } else {
            return ResponseEntity.status(422).build();
        }
    }

    //customers?contactName=Denis
    @GetMapping("/customers")
    @ResponseBody
    public List<CustomerEntity> getAllCustomers(@RequestParam(required = false) String contactName) {
        if (contactName == null) {
            return customerRepository.findAll();
        }

        return customerRepository.findAll()
                .stream()
                .filter(customerEntity -> customerEntity.getContactName().contains(contactName))
                .collect(Collectors.toList());
    }

    //customers/ALFKI
    @GetMapping("/customers/{id}")
    public Optional<CustomerEntity> getCustomerById(@PathVariable String id) {
        return customerRepository.findById(id);
    }

    //customers/city?cityName=London
    @GetMapping(value = "/customers/city", params = {"cityName"})
    @ResponseBody
    public List<CustomerEntity> getCustomerByCity(@RequestParam String cityName) {
        List<CustomerEntity> foundCustomersInCity = new ArrayList<>();
        for (CustomerEntity customer : customerRepository.findAll()) {
            if (customer.getCity() != null && customer.getCity().equalsIgnoreCase(cityName)) {
                foundCustomersInCity.add(customer);
            }
        }
        return foundCustomersInCity;
    }

    //customers/title?contactTitle=Owner
    @GetMapping(value = "/customers/title", params = {"contactTitle"})
    @ResponseBody
    public List<CustomerEntity> getCustomersByContactTitle(@RequestParam String contactTitle) {
        List<CustomerEntity> foundCustomersByTitle = new ArrayList<>();
        for (CustomerEntity customer : customerRepository.findAll()) {
            if (customer.getContactTitle() != null && customer.getContactTitle().equalsIgnoreCase(contactTitle)) {
                foundCustomersByTitle.add(customer);
            }
        }
        return foundCustomersByTitle;
    }
}
