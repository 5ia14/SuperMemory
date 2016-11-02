package com.example.admin.supermemory.model;

import android.media.Image;

/**
 * Created by Sadri on 02.11.2016.
 */
public class Card {

    private int value;
    private Image image;

    private boolean isFlipped;

    public Card(int value, Image image){
        this.value = value;
        this.image = image;
        this.isFlipped = false;
    }

    public int getValue() {
        return value;
    }

    public Image getImage() {
        return image;
    }

    public boolean compareCard(Card c){
        return c.image.equals(image) && (value == c.value);
    }

    public void flipCard(boolean flip){
        this.isFlipped = flip;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

}
