package com.example.myproject;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class Items {

    String text;
    int image;

    public Items(String text, int image){
        this.text=text;
        this.image=image;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }
}
