package com.example.admin.supermemory.model;

import android.media.Image;
import android.widget.ImageView;

import com.example.admin.supermemory.R;

/**
 * Created by Sadri on 02.11.2016.
 */
public class MemoryField {

    private Card[][] cards;

    public MemoryField(){
        cards = new Card[2][4]; //4 BILDER DESHALB 8 KARTEN
        setCards();
    }

    public Card getCard(int x, int y){
        return cards[x][y];
    }

    public void setCards(){
        ImageView[] images = new ImageView[4];
        images[0].setImageResource(R.drawable.bild0);
        images[1].setImageResource(R.drawable.bild1);
        images[2].setImageResource(R.drawable.bild2);
        images[3].setImageResource(R.drawable.bild3);

        int counter = 0;
        for(int a = 0; a < 2; a++){
            for(int b = 0; b < 4; b++){
                if(counter >= 4){
                    counter = 0;
                }
                cards[a][b] = new Card(counter, images[counter]);
                counter++;
            }
        }
    }
}