package com.example.projectname.Controller;

import com.example.projectname.exception.ProductNotFoundException;
import com.example.projectname.exception.UnAuthorizedException;
import com.example.projectname.model.Product;
import com.example.projectname.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @MockitoBean
    private ProductService productService;
    // getProductById
    @Test
    void testGetProductByIdPositiveCase() throws ProductNotFoundException, UnAuthorizedException {
        //Arrange
        Integer productId = 5;
        Product exceptedProduct=new Product();
        exceptedProduct.setId(productId);
        exceptedProduct.setTitle("Creating Dummy Sample product ");


        //mock service call
        when(productService.getProductById(productId))
                .thenReturn(exceptedProduct);


        //Act
         // i am writing test case for controller so i will not mock because ye real hai i'll mock productservice
        //ACT
        Product actualProduct=productController.getProductById(productId);

        //Assert
        assertEquals(exceptedProduct,actualProduct);

    }
    // good practice to write test case
    // test case should be independent to each other
    //fast
    // repeatble
    //no human intervention

}