package com.example.projectname.Controller;

import com.example.projectname.dto.CreateProductRequestDto;
import com.example.projectname.exception.ProductNotFoundException;
import com.example.projectname.model.Product;
import com.example.projectname.service.FakeStoreProductService;
import com.example.projectname.service.ProductService;
import com.example.projectname.service.SelfProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// rest controller--annotation hai jissay pta chlega ye ki spring ko ki special file hai
@RestController


// waiter of my project--controller
public class ProductController {
   // private FakeStoreProductService service;
    //-- now its time call our own dabaseae via product service interface
   // private ProductService productService; // we can't create object for interface eg;
    // private ProductService= new product service -- cant do that

    // i can do this below wala

//    private ProductService service = new SelfProductService();
//    private ProductService service2= new FakeStoreProductService();

    private ProductService service;

    public ProductController(@Qualifier("selfProductService") ProductService inputService) {
        this.service = inputService;
    }

    @GetMapping("/products/{id}")
    @Cacheable(value = "product", key = "#id")
    public Product getProductById(@PathVariable("id") Integer id) throws ProductNotFoundException {
        if (id == 10000) {
            throw new IllegalArgumentException("Id should not be 10000");
        }

        Product product = service.getProductById(id); // service = new SelfProductService()
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }

        return product;
    }
    @PostMapping("/products")
    @CachePut(value = "product", key = "#result.title")
    public Product createProduct(@RequestBody CreateProductRequestDto request) {
        // validation
        if(request.getDescription()==null)
        {
            throw new IllegalArgumentException("description cannot null");
        }
        if(request.getTitle()==null){
            throw new IllegalArgumentException("title cannot null");
        }
        // call service layer

        return service.createProduct(request.getTitle(), request.getImageURL(), request.getCategory().getTitle(),
                request.getDescription());



    }

     @GetMapping("/products")

    public ResponseEntity<List<Product>> getAllProduct(){


         // call service layer
         //throw new RuntimeException();
         List<Product> products= service.getAllProducts();
         return ResponseEntity.ok(products);

    }
    @PutMapping("/products/{id}")
    public void UpdateProduct(@PathVariable("id") Integer id){

    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Integer id){

    }

    @GetMapping("/products/{pageNo}/{pageSize}")
    public ResponseEntity<Page<Product>> getPaginatedProducts(@PathVariable("pageNo") int pageNo,
                                                              @PathVariable("pageSize") int pageSize) {
        Page<Product> products = service.getPaginatedProducts(pageNo, pageSize);
        return ResponseEntity.ok(products);
    }






}
