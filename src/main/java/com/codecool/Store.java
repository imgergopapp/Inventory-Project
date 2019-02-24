package com.codecool;

import java.util.List;

public abstract class Store {
    private void saveToXml (Product product){

    }

    //Abstract, implemented in PersistentStore
    protected abstract void storeProduct(Product product);

    protected  Product createProduct(String type, String name, int price, int size){
        
    }

    public List<Product> loadProducts(){

    }

    public void store(Product product){
        saveToXml();//not sure about method call order atm.
        storeProduct();

    }
}
