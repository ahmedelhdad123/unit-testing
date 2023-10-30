package org.example.store;

import lombok.Data;

@Data
public class Product {
    private String name;
    private int price;
    private int quantity;

    public Product() {
    }

    public Product(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }
}
