/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hulkstore.kardex.infrastructure.repository;

import com.hulkstore.kardex.domain.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 *
 * @author Administrador
 */
interface ReactiveMongoProductRepository extends ReactiveMongoRepository<Product, String> {
}

