package com.example.admin.supermemory.model;

import android.media.Image;

/**
 * Created by Sadri on 02.11.2016.
 */
public class Card {

    private int value;
    private Image image;

    public Card(int value, Image image){
        this.value = value;
        this.image = image;
    }

    public int getValue() {
        return value;
    }

    public Image getImage() {
        return image;
    }

    public boolean compareCard(Card c){
        if(c.image.equals(image) && (value == c.value)){
            return true;
        }
        return false;
    }
}
