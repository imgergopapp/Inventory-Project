package com.codecool;

public class CDProduct extends Product{

    private int numOfTracks;

    public CDProduct(String name, int price, ProductType type, int numOfTracks){
        super(name,price,type);
        this.numOfTracks = numOfTracks;
    }

    public int getNumOfTracks() {
        return numOfTracks;
    }

    @Override
    public String toString() {
        return "\nCD name: "  + getName() +
            " | Price: " + getPrice() +
            " | Tracks: " + numOfTracks +
            " | Type: " + getType();
    }
}
