package com.example.projectname.service;

import com.example.projectname.model.Category;
import com.example.projectname.model.Product;
import com.example.projectname.repository.CategoryRepo;
import com.example.projectname.repository.ProductRepo;
import com.example.projectname.repository.projection.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")

public class SelfProductService implements ProductService {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public SelfProductService(ProductRepo productRepo , CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;

    }
    @Override
    public Product getProductById(Integer id) {
        // get product by id
        // SQL QUERY--
        //SELECT * from  product where id =
//
//        System.out.println("Inside getProductById.....");
//       Product response = productRepo.findById(id).get();
//        System.out.println("Fetched Response." + response);
//        return response;


        System.out.println("Inside getProductById.....");
        Optional<Product> response = productRepo.findById(id);
        if (!response.isPresent()) {
            throw new IllegalArgumentException("Product not found");
        }
        System.out.println("Fetched product : " + response);

        /**
         * some sample test code below to test CAT REpo.
         */


        List<Category> categories = categoryRepo.findAll(); // ye fetch walaimplementa hai
        System.out.println("Fetched List of Categories....");

       List<Product> productList = categories.get(0).getProducts(); // fetch type= LAZY

        return response.get();


    }

    @Override

    public List<Product> getAllProducts() {
//        ProductProjection response = (ProductProjection) productRepo.getProductNameByTitle("phone samsung");
//        System.out.println("Fetched product : " + response.getDescription() + " " + response.getTitle());
        return productRepo.findAll();
    }
//    public List<Product> getAllProducts() {
////        Product response = (Product) productRepo.getProductNameByTitle("phone samsung");
////        System.out.println("Fetched product : " + response.getDescription() + " " + response.getTitle());
////        return productRepo.findAll();
//
//        Product response = (Product) productRepo.getProductNameByTitle("phone samsung");
//        System.out.println("Fetched product : " + response.getDescription() + " " + response.getTitle());
//        return productRepo.findAll();
//    }

    @Override
    public Product createProduct(String title, String imageURL, String catTitle, String description) {
        //Step1:
        validateInputRequest(title, imageURL, catTitle, description);

        // Step2:
        Product product = new Product();
        Category category = new Category();
        product.setImageURL(imageURL);
        product.setTitle(title);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        product.setDescription(description);

        // Step3: check if cat exists in the DB

        Category existingCategory = categoryRepo.findByTitle(catTitle).get();
        if (existingCategory == null) {

            category.setTitle(catTitle);
        }
        // saved category also.
        product.setCategory(category);

        // Finally save to the DB.
        Product response = productRepo.save(product);
        return response;
    }


    @Override
    public Page<Product> getPaginatedProducts(int pageNo, int pageSize) {
        // pageNo:1 & pageSize: 10
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        String sortBy = "title";
        Pageable pageableWithSort = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, sortBy);

        Page<Product> productPage = productRepo.findAll(pageableWithSort);

        return productPage;
    }
    private void validateInputRequest(String title, String imageURL, String catTitle, String description) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null");
        }
        /**
         * Add other validations here.
         */
    }
}
