package com.sparta.northwid.controllers;

import com.sparta.northwid.entities.ProductEntity;
import com.sparta.northwid.entities.SupplierEntity;
import com.sparta.northwid.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductsController {

    ProductsRepository productsRepository;

    @Autowired
    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public Optional<ProductEntity> getProductsById(@PathVariable Integer id) {
        return productsRepository.findById(id);
    }

    @GetMapping(value = "/products/supplier", params = "supplierId")
    @ResponseBody
    public List<ProductEntity> getProductsBySupplierId(@RequestParam Integer supplierId) {
        List<ProductEntity> foundProducts = new ArrayList<>();

        for (ProductEntity product : productsRepository.findAll()) {

            SupplierEntity supplierEntity = product.getSupplierID();

            if (supplierEntity.getId().equals(supplierId)) {
                foundProducts.add(product);
            }

        }
        return foundProducts;
    }
}
