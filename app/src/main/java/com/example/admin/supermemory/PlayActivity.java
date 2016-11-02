package com.example.admin.supermemory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.supermemory.model.MemoryField;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sadri on 02.11.2016.
 */
public class PlayActivity extends AppCompatActivity {

    private int score;
    private Date time;
    private MemoryField memoryField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        setUp();
    }

    public void setUp(){
        score = 0;
        time = Calendar.getInstance().getTime();
        memoryField = new MemoryField();

    }



}
