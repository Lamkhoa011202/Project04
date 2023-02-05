package com.example.project04.introActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project04.Login_Register_Activity.LoginActivity;
import com.example.project04.R;

public class IntroOneActivity extends AppCompatActivity {
    ImageView logo, imageViewbkhoa,bacgroundApp;
    TextView tvAppName;
    Animation left_to_right, right_to_left, topBottom,anibkhoa,anibg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        imageViewbkhoa = findViewById(R.id.imgbk);
        logo = findViewById(R.id.logo);
        tvAppName = findViewById(R.id.AppName);
        bacgroundApp=findViewById(R.id.bkapp);
        anibkhoa = AnimationUtils.loadAnimation(IntroOneActivity.this, R.anim.scale);
        anibg = AnimationUtils.loadAnimation(IntroOneActivity.this, R.anim.background_intro_animation);
        left_to_right = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        right_to_left = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        topBottom = AnimationUtils.loadAnimation(this, R.anim.bottom_top);
        logo.setAnimation(right_to_left);
        tvAppName.setAnimation(left_to_right);
        bacgroundApp.setAnimation(anibg);
        imageViewbkhoa.setAnimation(anibkhoa);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroOneActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
