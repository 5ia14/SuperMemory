package com.example.admin.supermemory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Sadri on 02.11.2016.
 */
public class MultiPlayActivity extends AppCompatActivity {
    /* put this into your activity class */
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter

            if (mAccel > 20) {
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

    private List<ImageButton> ib = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_player);

        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.memoryField);
        assert linearLayout != null;

        for (int index = 0; index < linearLayout.getChildCount(); ++index) {
            View nextChild = linearLayout.getChildAt(index);
            ib.add((ImageButton) nextChild);
        }

        // shuffleCards();
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
            Random rnd = new Random();
            int r = rnd.nextInt(200);

            b.animate().rotationY(r);
            linearLayout.addView(b/*, new GridLayout.LayoutParams(titleRowSpec , titleTxtSpecColumn*/);

            counter++;
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
