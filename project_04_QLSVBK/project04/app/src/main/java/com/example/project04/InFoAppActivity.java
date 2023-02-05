package com.example.project04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project04.Login_Register_Activity.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InFoAppActivity extends AppCompatActivity {

    TextView tvWebInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        tvWebInfo = findViewById(R.id.tv_web_info);
        tvWebInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hust.edu.vn"));
                startActivity(browserIntent);
            }
        });
    }
}
