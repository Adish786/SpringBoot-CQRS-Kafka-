package com.product.command.controller;

import com.product.command.dto.ProductEvent;
import com.product.command.entity.Product;
import com.product.command.repository.ProductRepo;
import com.product.command.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
@Autowired
    private ProductCommandService productService;
@Autowired
    private ProductRepo productRepo;


@PostMapping
    public Product createProduct( @RequestBody ProductEvent productEvent)
    {
        return productService.createProduct(productEvent);
    }

@PutMapping({"/{id}"})
    public Product updateProduct(@PathVariable("id") long id , @RequestBody ProductEvent productEvent)
    {
        return  productService.updateProduct(id,productEvent);
    }



}
