package com.example.ray.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ray.lostsystem.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public  void Next(View v){
        Intent second=new Intent(SignupActivity.this,Activity_Home.class);
        startActivity(second);
    }
}