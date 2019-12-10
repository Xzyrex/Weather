package com.example.weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity implements View.OnClickListener {
    Button finder;
    Button gps;
    StringsDataBase values;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        finder = findViewById(R.id.finder);
        gps = findViewById(R.id.gps);
        finder.setOnClickListener(menu.this);
        gps.setOnClickListener(menu.this);
        values = new StringsDataBase();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finder:
                intent = new Intent(menu.this, CityFinder.class);
                startActivity(intent);
                break;
            case R.id.gps:
                intent = new Intent(menu.this, GpsFinder.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}