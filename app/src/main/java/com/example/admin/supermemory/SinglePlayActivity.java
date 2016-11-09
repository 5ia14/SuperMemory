package com.example.admin.supermemory;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sadri on 03.11.2016.
 */
public class SinglePlayActivity extends AppCompatActivity {

    private int score = 0;
    private TextView scoreOut;

    private ImageButton cardOne;
    private ImageButton cardTwo;

    private SensorManager mSensorManager;
    private float mAcceleration;
    private float mAccelerationCurrent;
    private float mAccelerationLast;

    private List<ImageButton> ib = new ArrayList<>();

    public final int FULL_TURN = 360;

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelerationLast = mAccelerationCurrent;
            mAccelerationCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelerationCurrent - mAccelerationLast;
            mAcceleration = mAcceleration * 0.9f + delta; // perform low-cut filter

            if (mAcceleration > 20) {
                Toast toast = Toast.makeText(getApplicationContext(), "Shuffle", Toast.LENGTH_SHORT);
                toast.show();

                shuffleCards();

                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(400);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAcceleration = 0.00f;
        mAccelerationCurrent = SensorManager.GRAVITY_EARTH;
        mAccelerationLast = SensorManager.GRAVITY_EARTH;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_play);

        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.memoryField);
        assert linearLayout != null;

        for (int index = 0; index < linearLayout.getChildCount(); ++index) {
            View nextChild = linearLayout.getChildAt(index);
            ib.add((ImageButton) nextChild);
        }
        score = 0;

        linearLayout.removeAllViews();

        shuffleCards();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    private void shuffleCards() {
        List<Pair<Integer, Integer>> cords = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                cords.add(new Pair<>(i, j));
            }
        }

        Collections.shuffle(cords);

        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.memoryField);
        assert linearLayout != null;
        linearLayout.removeAllViews();

        int counter = 0;
        for (ImageButton b : ib) {
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();

            param.setMargins(20, 20, 20, 20);
            param.columnSpec = GridLayout.spec(cords.get(counter).first);
            param.rowSpec = GridLayout.spec(cords.get(counter).second);

            String uri = "@drawable/def";
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            b.setBackground(res);

            linearLayout.addView(b, param);

            counter++;
        }
    }

    public void showMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showSinglePlayer(View view) {
        Intent intent = new Intent(this, SinglePlayActivity.class);
        startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void handleCardClick(View view) {
        String uri = "@drawable/" + view.getTag();

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);

        view.animate().rotationX(FULL_TURN);
        view.setBackground(res);

        if (cardOne == null) {
            cardOne = (ImageButton) view;
        } else if (cardTwo == null) {
            cardTwo = (ImageButton) view;
            if (sameCard(cardOne, cardTwo)) {
                removeCard(cardOne);
                removeCard(cardTwo);
                scoreUp();
                cardOne = null;
                cardTwo = null;
            } else {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cardOne.setBackground(getResources().getDrawable(R.drawable.def));
                        cardTwo.setBackground(getResources().getDrawable(R.drawable.def));
                        cardOne = null;
                        cardTwo = null;
                    }
                }, 1000);
            }
        }
    }

    public void removeCard(ImageButton card) {
        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.memoryField);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            ImageButton child = (ImageButton) linearLayout.getChildAt(i);
            if (child.equals(card)) {
                linearLayout.removeViewAt(i);
            }
        }
    }

    public boolean sameCard(ImageButton card1, ImageButton card2) {
        if (card1.getTag() == card2.getTag()) {
            if (card1.getId() != card2.getId()) {
                return true;
            }
        }
        return false;
    }

    public void scoreUp() {
        score++;
        scoreOut = (TextView) findViewById(R.id.scoreOut);
        assert scoreOut != null;
        scoreOut.setText("Score: " + score);
        cardsAvailable();
    }

    public void cardsAvailable() {
        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.memoryField);
        assert linearLayout != null;
        if (linearLayout.getChildCount() == 0) {

            AlertDialog alertDialog = new AlertDialog.Builder(SinglePlayActivity.this).create();
            alertDialog.setTitle("YOU WON!");
            alertDialog.setMessage("You won the game!");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Back",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                            showMain(null);
                        }
                    });
            alertDialog.show();
        }
    }
}
