package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

public class edu_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_main);

        ImageButton 衛_心血管疾病_IB=findViewById(R.id.衛_心血管疾病_IB);
        衛_心血管疾病_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(edu_main.this,edu_01.class);
                 startActivity(intent);
            }
        });
        ImageButton 衛_慢性肺病_IB=findViewById(R.id.衛_慢性肺病_IB);
        衛_慢性肺病_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,edu_02.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_糖尿病_IB=findViewById(R.id.衛_糖尿病_IB);
        衛_糖尿病_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,edu_03.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_鬧中風_IB=findViewById(R.id.衛_鬧中風_IB);
        衛_鬧中風_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,edu_04.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_腎臟疾病_IB=findViewById(R.id.衛_腎臟疾病_IB);
        衛_腎臟疾病_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,edu_05.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_氣喘_IB=findViewById(R.id.衛_氣喘_IB);
        衛_氣喘_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,edu_06.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_過敏_IB=findViewById(R.id.衛_過敏_IB);
        衛_過敏_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,edu_07.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_痛風_IB=findViewById(R.id.衛_痛風_IB);
        衛_痛風_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,edu_08.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_急救知識站_IB=findViewById(R.id.衛_急救知識站_IB);
        衛_急救知識站_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,edu_09.class);
                startActivity(intent);
            }
        });

       ImageButton 通_通知_IB=findViewById(R.id.通_通知_IB);
        通_通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,noti.class);
                startActivity(intent);
            }
        });
        ImageButton 通_個人_IB=findViewById(R.id.通_個人_IB);
        通_個人_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,Personal.class);
                startActivity(intent);
            }
        });
        ImageButton 通_首頁_IB=findViewById(R.id.通_首頁_IB);
        通_首頁_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,Homepage.class);
                startActivity(intent);
            }
        });

        ImageButton 衛_居家檢測_IB=findViewById(R.id.衛_居家檢測_IB);
        衛_居家檢測_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_醫療小卡_IB=findViewById(R.id.衛_醫療小卡_IB);
        衛_醫療小卡_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,MedicalCard.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_衛教資訊_IB=findViewById(R.id.衛_衛教資訊_IB);
        衛_衛教資訊_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,edu_main.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_飲食推薦_IB=findViewById(R.id.衛_飲食推薦_IB);
        衛_飲食推薦_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,commendMain.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_即時定位_IB=findViewById(R.id.衛_即時定位_IB);
        衛_即時定位_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_main.this,CurrentLocation.class);
                startActivity(intent);
            }
        });



    }
}