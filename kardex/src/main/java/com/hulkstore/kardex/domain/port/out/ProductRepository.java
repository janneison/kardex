/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hulkstore.kardex.domain.port.out;

import com.hulkstore.kardex.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 *
 * @author Administrador
 */
public interface ProductRepository {
    Mono<Product> save(Product product);
    Mono<Product> findById(String id);
    Flux<Product> findAll();
}
