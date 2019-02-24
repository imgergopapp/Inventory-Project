package com.codecool;

import java.util.List;

public abstract class Store {
    private void saveToXml (Product product){

    }

    //Abstract, implemented in PersistentStore
    protected abstract void storeProduct(Product product);

    protected  Product createProduct(ProductType type, String name, int price, int size){
        if (type == ProductType.CD){
            return new CDProduct(name,price,size);
        }
        else {
            return new BookProduct(name,price,size);

        }
        
    }

    public List<Product> loadProducts(){

    }

    public void store(Product product){
        saveToXml(product);//not sure about method call order atm.
        storeProduct(product);

    }
}
