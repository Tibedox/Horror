package com.example.horror;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout bg;
    int screenWidth, screenHeight;
    Ghost[] ghost = new Ghost[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получаем ширину и высоту экрана
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        bg = findViewById(R.id.bg);
        for (int i = 0; i < ghost.length; i++) {
            ghost[i] = new Ghost(this);
        }

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < ghost.length; i++) {
                    ghost[i].move();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 10);
    }
}