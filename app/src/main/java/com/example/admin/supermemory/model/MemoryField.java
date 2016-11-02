package com.example.admin.supermemory.model;

/**
 * Created by Sadri on 02.11.2016.
 */
public class MemoryField {

    private Card[][] cards;

    public MemoryField(){
        cards = new Card[6][5];
        setCards();
    }

    public void setCards(){
        Card[] cardsL = new Card[15];
        for(int i = 0; i < 14; i++){
            //cardsL = new Card(i, android.R.drawable.);
        }

        for(int a = 0; a < 5; a++){
            for(int b = 0; b < 4; b++){
                //cards[a][b] = new Card(
            }
        }
        //setzt 15 random bilder auf 30 karten und setzt noch zu jeder card eine value
    }
}