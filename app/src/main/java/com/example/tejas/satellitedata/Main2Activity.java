package com.example.tejas.satellitedata;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Main2Activity extends AppCompatActivity {
    AnimationDrawable anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void health(View v){
        Intent in=new Intent(this,MainActivity.class);
        startActivity(in);

    }
}
