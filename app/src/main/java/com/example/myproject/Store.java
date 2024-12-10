package com.example.myproject;

public class Store {

    private String name;
    private String  rating;
    private int imageResource;;

    public Store(String name, String rating, int imageResource) {
        this.name = name;
        this.rating = rating;
        this.imageResource = imageResource;
    }

    public String  getRating() {
        return rating;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }
}