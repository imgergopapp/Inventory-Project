package com.codecool;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        StoreManager storeManager = new StoreManager();

        List<Product> products = storeManager.storageCapable.getAllProduct();
        Output.printProducts(products,"All products ");

        int totalPrice = storeManager.getTotalProductPrice();
        System.out.println("\nThe total price of all products is " + totalPrice + ".\n");

        storeManager.addBookProduct("Night Shift", 25, 336);
        storeManager.addCDProduct("AM", 18, 12);
        List<Product> storeProducts = storeManager.persistentStore.getStoredProducts() ;
        Output.printProducts(storeProducts, "Stored products: ");

        products = storeManager.storageCapable.getAllProduct();
        Output.printProducts(products,"All products: ");

        totalPrice = storeManager.getTotalProductPrice();
        System.out.println("The total price of all products is " + totalPrice + ".");
    }
}
