/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hulkstore.kardex.unit;

import com.hulkstore.kardex.application.services.KardexServiceImpl;
import com.hulkstore.kardex.domain.model.Product;
import com.hulkstore.kardex.domain.port.out.ProductRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class KardexServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private KardexServiceImpl kardexService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() {
        Product product = new Product("1", "T-Shirt", "Marvel T-Shirt", 10);
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(product));

        Mono<Product> result = kardexService.addProduct(product);

        StepVerifier.create(result)
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    void testGetProductById() {
        Product product = new Product("1", "T-Shirt", "Marvel T-Shirt", 10);
        when(productRepository.findById("1")).thenReturn(Mono.just(product));

        Mono<Product> result = kardexService.getProductById("1");

        StepVerifier.create(result)
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product("1", "T-Shirt", "Marvel T-Shirt", 10);
        Product product2 = new Product("2", "Mug", "Marvel Mug", 5);
        when(productRepository.findAll()).thenReturn(Flux.just(product1, product2));

        Flux<Product> result = kardexService.getAllProducts();

        StepVerifier.create(result)
                .expectNext(product1)
                .expectNext(product2)
                .verifyComplete();
    }

    @Test
    void testAddStock() {
        Product product = new Product("1", "T-Shirt", "Marvel T-Shirt", 100);
        when(productRepository.findById("1")).thenReturn(Mono.just(product));
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(product));

        Mono<Void> result = kardexService.addStock("1", 50);

        StepVerifier.create(result)
                .verifyComplete();

        verify(productRepository, times(1)).save(any(Product.class));
        assertEquals(150, product.getStock());
    }

    @Test
    void testRemoveStock() {
        Product product = new Product("1", "T-Shirt", "Marvel T-Shirt", 100);
        when(productRepository.findById("1")).thenReturn(Mono.just(product));
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(product));

        Mono<Void> result = kardexService.removeStock("1", 50);

        StepVerifier.create(result)
                .verifyComplete();

        verify(productRepository, times(1)).save(any(Product.class));
        assertEquals(50, product.getStock());
    }

    @Test
    void testRemoveStockNotEnough() {
        Product product = new Product("1", "T-Shirt", "Marvel T-Shirt", 100);
        when(productRepository.findById("1")).thenReturn(Mono.just(product));

        Mono<Void> result = kardexService.removeStock("1", 150);

        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();

        verify(productRepository, never()).save(any(Product.class));
    }
}
