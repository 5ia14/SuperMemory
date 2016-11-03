package com.example.admin.supermemory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import com.example.admin.supermemory.model.*;
/**
 * Created by Sadri on 03.11.2016.
 */
public class SinglePlayActivity extends AppCompatActivity {

    private int score;
    private int time;
    private MemoryField memoryField;
    private final TextView timeView = (TextView) findViewById(R.id.timeOut);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_play);
        startTimer();
    }

    public void setUp(){
        score = 0;
        time = 0;
        startTimer();
        memoryField = new MemoryField();
    }

    public void startTimer(){
        final TextView timeView = (TextView) findViewById(R.id.timeOut);
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeTick();
            }
        }, 1000, 1000);
    }

    public void timeTick(){
        timeView.setText(R.string.timeLabelT + time);
        time++;
    }
}
