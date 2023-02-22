package com.interview.com.java;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.interview.com.R;
import com.interview.com.serview.AAservice;

class KotlinJavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, AAservice.class);
        startService(intent);
    }


    class aaa{
        public aaa() {
        }
        public aaa(String a) {
        }
    }
}
