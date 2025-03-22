package com.example.projectname.service;

import com.example.projectname.dto.FakeStoreResponseDTO;
import com.example.projectname.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getProductById(Integer id);



    List<Product> getAllProducts();

    Product createProduct(String title, String imageURL, String catTitle, String description);

    Page<Product> getPaginatedProducts(int pageNo, int pageSize);




    /**
     * This is an interface for my ProductService.
     */
//    public interface ProductService {
//        Product getProductById(Integer id);
//
//        List<Product> getAllProducts();
//
//        Product createProduct(String title, String imageURL, String catTitle, String description);
//
//    }
}

