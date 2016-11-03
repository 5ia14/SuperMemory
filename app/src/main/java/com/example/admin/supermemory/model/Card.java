package com.example.admin.supermemory.model;

/**
 * Created by Sadri on 02.11.2016.
 */
public class Card {

    private int value;
    private int image;

    private boolean isFlipped;

    public Card(int value, int image){
        this.value = value;
        this.image = image;
        this.isFlipped = false;
    }

    public int getValue() {
        return value;
    }

    public int getImage() {
        return image;
    }

    public boolean compareCard(Card c){
        return (image == c.image) && (value == c.value);
    }

    public void flipCard(boolean flip){
        this.isFlipped = flip;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

}
