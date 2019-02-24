package com.codecool;

import java.util.List;

public class StoreManager  {
    private StorageCapable storageCapable;

    public void addStorage(StorageCapable storage){
        storageCapable = new StorageCapable() {

            @Override
            public List<Product> getAllProduct() {
                return null;
            }

            @Override
            public void storeCDProduct(String name, int price, int tracks) {

            }

            @Override
            public void storeBookProduct(String name, int price, int pages) {

            }
        };
    }
    public void addCDProduct(String name, int price, int tracks){
        storageCapable.storeCDProduct(name, price, tracks);

    }
    public void addBookProduct(String name, int price, int pages){
        storageCapable.storeBookProduct(name, price, pages);
    }

    public String listProducts(){

    }

    public int getTotalProductPrice(){

    }
}
