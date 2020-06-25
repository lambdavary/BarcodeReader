package com.example.barcodereader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.barcodereader.views.MainScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashEffect();
    }

    public void splashEffect(){
        short SPLASH_DELAY = 1000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainScreen.class);
                startActivity(intent);
            }
        }, SPLASH_DELAY);
    }
}