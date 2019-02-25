package com.codecool;

public class PersistentStore extends Store {

    public void storeProduct(Product product) {
        addToStoredProducts(product);
        System.out.println(product.getName() + " added to Stored product list.");
    }
}
