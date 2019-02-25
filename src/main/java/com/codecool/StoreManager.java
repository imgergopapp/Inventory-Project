package com.codecool;

import java.util.List;

public class StoreManager {

    PersistentStore persistentStore = new PersistentStore();

    StorageCapable storageCapable = new StorageCapable() {

        @Override
        public List<Product> getAllProduct() {
            List<Product> products = persistentStore.loadProducts();
            return products;
        }

        @Override
        public void storeCDProduct(String name, int price, int tracks) {
            Product product = persistentStore.createProduct(ProductType.CD, name, price, tracks);
            persistentStore.store(product);
        }

        @Override
        public void storeBookProduct(String name, int price, int pages) {
            Product product = persistentStore.createProduct(ProductType.BOOK, name, price, pages);
            persistentStore.store(product);
        }
    };

    public void addCDProduct(String name, int price, int tracks) {
        storageCapable.storeCDProduct(name, price, tracks);
    }

    public void addBookProduct(String name, int price, int pages) {
        storageCapable.storeBookProduct(name, price, pages);
    }

    public String listProducts() {
        List<Product> products = storageCapable.getAllProduct();
        String productList = " ";
        for (Product product : products) {
            productList += product.getName() + ", ";
        }
        return productList.substring(0, productList.length() - 2);
    }

    public int getTotalProductPrice() {
        List<Product> products = storageCapable.getAllProduct();
        int sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }
}
