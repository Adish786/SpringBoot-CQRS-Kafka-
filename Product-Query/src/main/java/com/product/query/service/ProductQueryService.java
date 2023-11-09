package com.product.query.service;

import com.product.query.dto.ProductEvent;
import com.product.query.entity.Product;
import com.product.query.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryService {

    @Autowired
    private ProductRepo repo;
    public List<Product> getProducts(){
        return  repo.findAll();
    }
    @KafkaListener(topics = "product-event-topic",groupId = "product-event-group")
    public void processProductEvents(ProductEvent productEvent){
        Product product = productEvent.getProduct();
        if(productEvent.getEventType().equals("CreateProduct")){
            repo.save(product);
        }
        if(productEvent.getEventType().equals("UpdateProduct")){
           Product existingProduct =  repo.findById(product.getId()).get();
           existingProduct.setName(product.getName());
           existingProduct.setPrice(product.getPrice());
           existingProduct.setDescription(product.getDescription());
           repo.save(existingProduct);
        }
    }
}
