package com.codecool;

public class BookProduct extends Product {

    private int numOfPages;

    public BookProduct(String name, int price, ProductType type, int numOfPages) {
        super(name, price, type);
        this.numOfPages = numOfPages;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    @Override
    public String toString() {
        return "Book name: " + getName() +
            " | Price: " + getPrice() +
            " | Pages: " + numOfPages +
            " | Type: " + getType();
    }
}
