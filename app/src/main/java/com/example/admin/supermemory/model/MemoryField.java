package com.example.admin.supermemory.model;

import com.example.admin.supermemory.R;

/**
 * Created by Sadri on 02.11.2016.
 */
public class MemoryField {

    private Card[][] cards;

    public MemoryField(){
        cards = new Card[5][6];
        setCards();
    }

    public void setCards(){
        Card[] cardsL = new Card[15];
        for(int i = 0; i < 14; i++){
             // cardsL = new Card(i, R.drawable.bild0);
        }

        int counter = 1;
        for(int a = 0; a < 5; a++){
            for(int b = 0; b < 6; b++){
                if(counter > 15){
                    counter = 0;
                }
                cards[a][b] = cardsL[counter];
                counter++;
            }
        }
        //setzt 15 random bilder auf 30 karten und setzt noch zu jeder card eine value
    }
}