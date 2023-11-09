package com.product.command.service;

import com.product.command.dto.ProductEvent;
import com.product.command.entity.Product;
import com.product.command.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    public Product createProduct(ProductEvent productEvent)
    {

        Product productDO= productRepo.save(productEvent.getProduct());
        ProductEvent event = new ProductEvent("CreateProduct",productDO);
        kafkaTemplate.send("product-event-topic",event);
        return productDO;
    }

    public Product updateProduct(Long id , ProductEvent productEvent)
    {
        Product existingProduct = productRepo.findById(id).get();
        Product newProduct = productEvent.getProduct();
        existingProduct.setName(newProduct.getName());
        existingProduct.setDescription(newProduct.getDescription());
        existingProduct.setPrice(newProduct.getPrice());
        Product productDO=  productRepo.save(existingProduct);
        ProductEvent event =new ProductEvent("UpdateProduct",productDO);
        kafkaTemplate.send("product-event-topic",event);
        return productDO;
    }
}
