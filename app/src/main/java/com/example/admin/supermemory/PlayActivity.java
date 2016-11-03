package com.example.admin.supermemory;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.admin.supermemory.model.MemoryField;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Sadri on 02.11.2016.
 */
public class PlayActivity extends AppCompatActivity {

    private int score;
    private int time;
    private MemoryField memoryField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_player);
        setUp();
    }

    public PlayActivity(){
        System.out.println("Baum");
    }

    public void setUp(){
        score = 0;
        time = 0;
        startTimer();
        memoryField = new MemoryField();
    }

    public void startTimer(){
        final TextView tv = (TextView) findViewById(R.id.timeOut);
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tv.setText(R.string.timeLabelT + " " + time);
                time++;

            }
        }, 1000, 1000);

    }

    public void connectMemoryFiled(){

    }


}
