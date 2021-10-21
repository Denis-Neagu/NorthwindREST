package com.sparta.northwid.controllers;

import com.sparta.northwid.entities.CustomerEntity;
import com.sparta.northwid.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    CustomersRepository customerRepository;

    @Autowired
    public CustomerController(CustomersRepository customersRepository) {
        this.customerRepository = customersRepository;
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

    //customers/company?companyName=IT
    @GetMapping("/customers/company")
    @ResponseBody
    public List<CustomerEntity> getCustomersByCompany(@RequestParam String companyName) {
        return customerRepository.findAll()
                .stream()
                .filter(customerEntity -> customerEntity.getCompanyName().contains(companyName))
                .collect(Collectors.toList());
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
