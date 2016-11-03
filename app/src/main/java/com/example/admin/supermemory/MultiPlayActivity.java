package com.example.admin.supermemory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Sadri on 02.11.2016.
 */
public class MultiPlayActivity extends AppCompatActivity {

    private List<ImageButton> ib = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_player);

        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.memoryField);
        assert linearLayout != null;

        for (int index = 0; index < linearLayout.getChildCount(); ++index) {
            View nextChild = linearLayout.getChildAt(index);
            ib.add((ImageButton) nextChild);
        }

        shuffleCards();
    }

    private void shuffleCards() {
        Collections.shuffle(ib);

        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.memoryField);
        assert linearLayout != null;
        linearLayout.removeAllViews();

        for (ImageButton b : ib) {
            b.animate().rotationX(50);

            Random rnd = new Random();
            int r = rnd.nextInt(300);

            b.setRotationY(r);
            linearLayout.addView(b);
        }
    }

    public void showMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showMultiPlayer(View view) {
        Intent intent = new Intent(this, MultiPlayActivity.class);
        startActivity(intent);
    }

    public void handleCardClick(View view) {
        // shuffleCards();
        view.animate().rotationX(180).rotationY(180);
    }
}
