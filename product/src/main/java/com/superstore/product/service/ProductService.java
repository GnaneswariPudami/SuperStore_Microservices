package com.superstore.product.service;

import com.superstore.product.entity.Product;
import com.superstore.product.repository.ProductRepository;
import com.superstore.product.response.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ModelMapper mapper;

    public Product getProductById(int product_id){
        Product product = productRepo.findById(product_id).get();
        return product;
    }

    public ProductResponse getProductById2(int product_id){
        Optional<Product> product = productRepo.findById(product_id);
        ProductResponse productResponse = mapper.map(product.get(), ProductResponse.class);
        return productResponse;
    }

    public void createNewProduct(Product product){
        productRepo.save(product);
    }
}
