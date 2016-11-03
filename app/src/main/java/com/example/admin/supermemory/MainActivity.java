package com.example.admin.supermemory;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showCredits(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Credits");
        alertDialog.setMessage("Ruben Allenspach\nSadri Rammal");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.memoryField);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                ImageButton btn = new ImageButton(this);

                assert linearLayout != null;
                linearLayout.addView(btn, i, j);
            }
        }
    }

    public void showSinglePlayer(View view) {
        setContentView(R.layout.single_player);
    }

    public void showMultiPlayer(View view) {
        setContentView(R.layout.multi_player);
    }

    public void showMain(View view) {
        setContentView(R.layout.activity_main);
    }

    public void handleCardClick(View view) {
        view.animate().rotationX(180).rotationY(180);
        view.clearAnimation();
    }
}
