package com.product.query.controller;

import com.product.query.dto.ProductEvent;
import com.product.query.entity.Product;
import com.product.query.repository.ProductRepo;
import com.product.query.service.ProductQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductQueryService productQuery;
    private ProductRepo repo;
    public ProductController(ProductQueryService productQuery, ProductRepo repo) {
        this.productQuery = productQuery;
        this.repo = repo;
    }
    @GetMapping
    public List<Product> getProducts() {
        return  productQuery.getProducts();
    }





}
