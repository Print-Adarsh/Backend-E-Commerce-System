package com.example.projectname.service;

import com.example.projectname.dto.FakeStoreResponseDTO;
import com.example.projectname.model.Category;
import com.example.projectname.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProductById(Integer id) {
        Product product = new Product();
        // call fakestore api--rest template

        ResponseEntity<FakeStoreResponseDTO>fakeStoreResponse=
        restTemplate.getForEntity( "https://fakestoreapi.com/products/" + id, FakeStoreResponseDTO.class);


        //get the response
        FakeStoreResponseDTO response =fakeStoreResponse.getBody();
        // some validation
        if(response==null) {
            throw new IllegalArgumentException("FakeStoreProduct not found");
        }
        // convert the response model to product
        product = convertFakeStoreResponseToProduct(response);
        //return
        return product;
    }

    public Product convertFakeStoreResponseToProduct(FakeStoreResponseDTO response) {
        Product product = new Product();
        Category category = new Category();
        category.setTitle(response.getCategory());

        product.setId(response.getId());
        product.setCategory(category);
        product.setDescription(response.getDescription());
        product.setImageURL(response.getImage());
        product.setTitle(response.getTitle());

        return product;
    }
    @Override
    public List<Product> getAllProducts() {
        List<Product> response = new ArrayList<>();
        // call fakestore api
        ResponseEntity<FakeStoreResponseDTO[]> fakeStoreProducts=
        restTemplate.getForEntity( "https://fakestoreapi.com/products", FakeStoreResponseDTO[].class);

        System.out.println("Status code: " + fakeStoreProducts.getStatusCode());
        // next step:

        for (FakeStoreResponseDTO fakeStoreDTO : fakeStoreProducts.getBody()) {
            response.add(convertFakeStoreResponseToProduct(fakeStoreDTO));
        }
        return response;
    }

    @Override
    public Product createProduct(String title, String imageURL, String catTitle, String description) {
        Product response;

        FakeStoreResponseDTO requestBody = new FakeStoreResponseDTO();
        requestBody.setCategory(catTitle);
        requestBody.setDescription(description);
        requestBody.setTitle(title);
        requestBody.setImage(imageURL);

        ResponseEntity<FakeStoreResponseDTO> fakeStoreResponse =
                restTemplate.postForEntity("https://fakestoreapi.com/products", requestBody, FakeStoreResponseDTO.class);

        System.out.println("Status code: " + fakeStoreResponse.getStatusCode());

        response = convertFakeStoreResponseToProduct(fakeStoreResponse.getBody());
        return response;
    }

    @Override
    public Page<Product> getPaginatedProducts(int pageNo, int pageSize) {
        // dummy implementation.
        return null;
    }



}
