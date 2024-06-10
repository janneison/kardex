/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hulkstore.kardex.integration;

import com.hulkstore.kardex.application.services.KardexServiceImpl;
import com.hulkstore.kardex.domain.model.Product;
import com.hulkstore.kardex.infrastructure.controller.ProductController;
import com.hulkstore.kardex.domain.port.out.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 *
 * @author Administrador
 */
@WebFluxTest(controllers = ProductController.class)
@Import(KardexServiceImpl.class)
public class ProductControllerIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product product1 = new Product("1", "T-Shirt", "Marvel T-Shirt", 100);
        Product product2 = new Product("2", "Mug", "Marvel Mug", 50);

        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(product1));
        when(productRepository.findById("1")).thenReturn(Mono.just(product1));
        when(productRepository.findAll()).thenReturn(Flux.just(product1, product2));
        when(productRepository.findById("2")).thenReturn(Mono.just(product2));
    }
    

    @Test
    void testAddStock() {
        webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/api/products/1/addStock")
                        .queryParam("quantity", 5)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }

    @Test
    void testRemoveStock() {
        webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/api/products/1/removeStock")
                        .queryParam("quantity", 5)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }
}
