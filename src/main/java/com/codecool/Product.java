package com.codecool;

public abstract class  Product {
    private String name;
    private int price;
    private ProductType type;

    public Product (String name, int price, ProductType type){
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

}
