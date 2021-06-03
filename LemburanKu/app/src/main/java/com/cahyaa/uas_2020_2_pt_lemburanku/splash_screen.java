package com.cahyaa.uas_2020_2_pt_lemburanku;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    Animation bottomAnim;
    ImageView splash_imageView;
    TextView splash_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        splash_imageView = findViewById(R.id.splash_imageView);
        splash_textView = findViewById(R.id.splash_textView);

        splash_imageView.setAnimation(bottomAnim);
        splash_textView.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent intent = new Intent(splash_screen.this, BotnavActivity.class);
               startActivity (intent);
               finish();
            }
        }, SPLASH_SCREEN);
    }
}