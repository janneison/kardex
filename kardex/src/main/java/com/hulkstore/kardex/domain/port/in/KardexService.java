/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hulkstore.kardex.domain.port.in;
import com.hulkstore.kardex.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 *
 * @author Administrador
 */
public interface KardexService {
    public Mono<Product> addProduct(Product product);
    public Mono<Product> getProductById(String id);
    public Flux<Product> getAllProducts();
    public Mono<Void> addStock(String id, int quantity);
    public Mono<Void> removeStock(String id, int quantity);
}
