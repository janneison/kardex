/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hulkstore.kardex.domain.model;

/**
 *
 * @author Administrador
 */
public class Product {
    private String id;
    private String name;
    private String description;
    private int stock;

    public Product(String id, String name, String description, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.description = description;
    }
    
    public Product() {
        
    }
    
    // Getters y setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        if (stock<0){
            stock = stock * -1;
        }
        this.stock = stock;
    }
    
}
