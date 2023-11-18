package com.example.horror;

import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class Ghost {
    MainActivity main;
    ImageView ghostView;
    float x, y;
    float vx, vy;
    int width, height;

    Ghost(MainActivity main){
        this.main = main;
        Random rnd = new Random();
        vx = rnd.nextFloat()*20-10;
        vy = rnd.nextFloat()*20-10;
        width = rnd.nextInt(100)+50;
        height = width*2;

        ghostView = new ImageView(main.getApplicationContext());
        ghostView.setImageResource(R.drawable.ghost);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(width, height);
        ghostView.setLayoutParams(params);
        x = main.screenWidth/2f - width/2f;
        y = main.screenHeight/2f - height/2f;
        ghostView.setX(x);
        ghostView.setY(y);
        main.bg.addView(ghostView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = -main.screenWidth;
                vx = vy = 0;
            }
        };
        ghostView.setOnClickListener(listener);
    }

    void move(){
        x += vx;
        if(x > main.screenWidth -ghostView.getWidth() | x<0) vx = -vx;
        ghostView.setX(x);

        y += vy;
        if(y > main.screenHeight -ghostView.getHeight() | y<0) vy = -vy;
        ghostView.setY(y);
    }
}
