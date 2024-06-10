/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hulkstore.kardex.infrastructure.repository;

import com.hulkstore.kardex.domain.model.Product;
import com.hulkstore.kardex.domain.port.out.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 *
 * @author Administrador
 */
@Repository
public class MongoProductRepository implements ProductRepository {

    private final ReactiveMongoProductRepository reactiveMongoProductRepository;

    @Autowired
    public MongoProductRepository(ReactiveMongoProductRepository reactiveMongoProductRepository) {
        this.reactiveMongoProductRepository = reactiveMongoProductRepository;
    }

    @Override
    public Mono<Product> save(Product product) {
        return reactiveMongoProductRepository.save(product);
    }

    @Override
    public Mono<Product> findById(String id) {
        return reactiveMongoProductRepository.findById(id);
    }

    @Override
    public Flux<Product> findAll() {
        return reactiveMongoProductRepository.findAll();
    }
}

