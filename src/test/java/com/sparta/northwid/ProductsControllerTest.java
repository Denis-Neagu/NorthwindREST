package com.sparta.northwid;

import com.sparta.northwid.controllers.ProductsController;
import com.sparta.northwid.entities.ProductEntity;
import com.sparta.northwid.entities.SupplierEntity;
import com.sparta.northwid.repositories.ProductsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductsControllerTest {

    @Mock
    ProductsRepository productsRepository;
    @InjectMocks
    ProductsController productsController;

    @Test
    void getProductsBySupplierIdReturnsCorrectSupplier() {
        SupplierEntity supplierEntity = new SupplierEntity();

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName("duracell");
        productEntity.setSupplierID(supplierEntity);

        supplierEntity.setId(70);

        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(productEntity);


        Mockito.when(productsRepository.findAll()).thenReturn(productEntityList);

        List<ProductEntity> result = productsController.getProductsBySupplierId(70);

        Assertions.assertTrue(result.size() > 0);
        Assertions.assertEquals(productEntity, result.get(0));
        Assertions.assertEquals(productEntity.getSupplierID(), supplierEntity);
    }

}
