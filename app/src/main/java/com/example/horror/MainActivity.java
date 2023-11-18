package com.example.horror;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout bg;
    int screenWidth, screenHeight;
    Ghost[] ghost = new Ghost[100];

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
            ghost[i] = new Ghost();
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

    class Ghost {
        ImageView ghostView;
        float x, y;
        float vx, vy;
        int width, height;

        Ghost(){
            Random rnd = new Random();
            vx = rnd.nextFloat()*20-10;
            vy = rnd.nextFloat()*20-10;
            width = rnd.nextInt(100)+50;
            height = width*2;

            ghostView = new ImageView(getApplicationContext());
            ghostView.setImageResource(R.drawable.ghost);
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(width, height);
            ghostView.setLayoutParams(params);
            x = screenWidth/2f - width/2f;
            y = screenHeight/2f - height/2f;
            ghostView.setX(x);
            ghostView.setY(y);
            bg.addView(ghostView);
        }

        void move(){
            x += vx;
            if(x > screenWidth -ghostView.getWidth() | x<0) vx = -vx;
            ghostView.setX(x);

            y += vy;
            if(y > screenHeight -ghostView.getHeight() | y<0) vy = -vy;
            ghostView.setY(y);
        }
    }
}