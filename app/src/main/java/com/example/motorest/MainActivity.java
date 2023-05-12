package com.example.motorest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void add_window(View view) {
        Intent myIntent = new Intent(MainActivity.this, AddReview.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void view_window(View view) {
        Intent myIntent = new Intent(MainActivity.this, ViewReview.class);
        MainActivity.this.startActivity(myIntent);
    }


}