/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hulkstore.kardex.application.services;

import com.hulkstore.kardex.domain.port.in.KardexService;
import com.hulkstore.kardex.domain.model.Product;
import com.hulkstore.kardex.domain.port.out.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author Administrador
 */
@Service
public class KardexServiceImpl implements KardexService{
    private final ProductRepository productRepository;

    public KardexServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Override
    public Mono<Product> addProduct(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Mono<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Mono<Void> addStock(String id, int quantity) {
        if (quantity<1){
            return Mono.error(new RuntimeException("Cantidad no puede ser negativa"));
        }
        return productRepository.findById(id)
                .flatMap(product -> {
                    product.setStock(product.getStock() + quantity);
                    return productRepository.save(product);
                }).then();
    }
    @Override
    public Mono<Void> removeStock(String id, int quantity) {
        if (quantity<1){
            return Mono.error(new RuntimeException("Cantidad no puede ser negativa"));
        }
        return productRepository.findById(id)
                .flatMap(product -> {
                    if (product.getStock() < quantity) {
                        return Mono.error(new RuntimeException("Esta tratando de obtener mas productos de los disponibles"));
                    }
                    product.setStock(product.getStock() - quantity);
                    return productRepository.save(product);
                }).then();
    }
}
