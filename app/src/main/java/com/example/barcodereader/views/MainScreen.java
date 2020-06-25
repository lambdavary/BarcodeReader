package com.example.barcodereader.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.example.barcodereader.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReadFragment()).commit();

        BottomNavigationView navigationView = findViewById(R.id.bottom_navbar);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.navbar_read:
                        fragment = new ReadFragment();
                        break;
                    case R.id.navbar_generate:
                        fragment = new GenerateFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;
            }
        });
    }
}