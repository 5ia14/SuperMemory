package com.example.admin.supermemory;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_play);
    }

    public void setUp(){
        score = 0;
        time = 0;
       //  startTimer();
        memoryField = new MemoryField();
    }

    public void onImage(ImageButton ib){
        String id = "" + ib.getId();
        int x = Integer.parseInt(id.substring(1, 2));
        int y =Integer.parseInt(id.substring(0, 1));
        ib.setImageDrawable(memoryField.getCard(x,y).getImage().getDrawable());
    }

   /* public void startTimer(){
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        timeTick();
                    }
                });
            }
        }, 1000, 1000);
    }

    public void timeTick(){
        TextView timeView = (TextView) findViewById(R.id.timeOut);
        System.out.println(time);
        timeView.setText(R.string.timeLabelT + time);
        time++;
    }*/
}
