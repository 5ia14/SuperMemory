package com.example.admin.supermemory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showSinglePlayer(View view) {
        Intent intent = new Intent(this, SinglePlayActivity.class);
        startActivity(intent);
    }

    public void showMultiPlayer(View view) {
        Intent intent = new Intent(this, MultiPlayActivity.class);
        startActivity(intent);
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
    }
}
