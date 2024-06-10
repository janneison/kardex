/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hulkstore.kardex.infrastructure.controller;
import com.hulkstore.kardex.domain.model.Product;
import com.hulkstore.kardex.domain.port.in.KardexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author Administrador
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final KardexService kardexService;

    @Autowired
    public ProductController(KardexService kardexService) {
        this.kardexService = kardexService;
    }

    @PostMapping
    public Mono<Product> addProduct(@RequestBody Product product) {
        return kardexService.addProduct(product);
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable String id) {
        return kardexService.getProductById(id);
    }

    @GetMapping
    public Flux<Product> getAllProducts() {
        return kardexService.getAllProducts();
    }

    @PostMapping("/{id}/addStock")
    public Mono<Void> addStock(@PathVariable String id, @RequestParam int quantity) {
        return kardexService.addStock(id, quantity);
    }

    @PostMapping("/{id}/removeStock")
    public Mono<Void> removeStock(@PathVariable String id, @RequestParam int quantity) {
        return kardexService.removeStock(id, quantity);
    }
}