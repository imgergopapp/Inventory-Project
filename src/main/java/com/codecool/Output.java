package com.codecool;

import java.util.List;

public class Output {
    public static void printProducts(List<Product> products,String message){
        System.out.println("\n" + message);
        for(Product product : products){
            System.out.println(product);
        }
    }


}
