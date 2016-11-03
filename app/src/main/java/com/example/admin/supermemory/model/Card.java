package com.example.admin.supermemory.model;

import android.widget.ImageView;

/**
 * Created by Sadri on 02.11.2016.
 */
public class Card {

    private int value;
    private ImageView image;

    private boolean isFlipped;

    public Card(int value, ImageView image){
        this.value = value;
        this.image = image;
        this.isFlipped = false;
    }

    public int getValue() {
        return value;
    }

    public ImageView getImage() {
        return image;
    }

    public boolean compareCard(Card c){
        return (image.equals(c.image)) && (value == c.value);
    }

    public void flipCard(boolean flip){
        this.isFlipped = flip;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

}
