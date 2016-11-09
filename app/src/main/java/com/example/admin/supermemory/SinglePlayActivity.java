package com.example.admin.supermemory;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewGroupCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.example.admin.supermemory.model.*;
/**
 * Created by Sadri on 03.11.2016.
 */
public class SinglePlayActivity extends AppCompatActivity {

    /*
    private long startTime  = 0L;
    private TextView timerValue;
    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;


    private MemoryField memoryField;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_play);
        startTimer();
    }

    public void setUp(){
        score = 0;
        startTimer();
        memoryField = new MemoryField();
    }


    public void startTimer(){
        timerValue = (TextView) findViewById(R.id.timeOut);
        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }
    };*/

    private SensorManager mSensorManager;
    private float mAcceleration; // acceleration apart from gravity
    private float mAccelerationCurrent; // current acceleration including gravity
    private float mAccelerationLast; // last acceleration including gravity

    private List<ImageButton> ib = new ArrayList<>();

    public final int HALF_TURN = 180;
    public final int QUARTER_TURN = 90;

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

                //shuffleCards();

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
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
            param.setMargins(20, 20, 20, 20);
            param.columnSpec = GridLayout.spec(cords.get(counter).first);
            param.rowSpec = GridLayout.spec(cords.get(counter).second);

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
        view.animate().rotationX(QUARTER_TURN).rotationY(QUARTER_TURN);

        ImageButton ib = (ImageButton) view;
        String id = "" + ib.getId();
        ib.setBackground(getDrawable(R.drawable.bild0));

        view.animate().rotationX(QUARTER_TURN).rotationY(QUARTER_TURN);
    }
}
