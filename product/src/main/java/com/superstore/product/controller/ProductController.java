package com.superstore.product.controller;

import com.superstore.product.entity.Product;
import com.superstore.product.response.ProductResponse;
import com.superstore.product.service.ProductService;
import com.superstore.product.service.SqsListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-service")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SqsListenerService sqsListenerService;

    @GetMapping("/product")
    private ResponseEntity<ProductResponse> getProductDetailsById(int product_id) {
        ProductResponse product = productService.getProductById2(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/product/getmessage")
    private String getProductMessage() throws Exception{
        sqsListenerService.listenForSqsMessages();
        return "Success";
    }

   /* @GetMapping("/product")
    private ResponseEntity<Product> getProductDetailsByIdPathParam(@PathParam("product_id") int product_id) {
        Product product = productService.getProductById(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }*/

    @PostMapping("/product")
    private String saveProductDetails(@RequestBody Product product){
        try {
            productService.createNewProduct(product);
            return "Success";
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
