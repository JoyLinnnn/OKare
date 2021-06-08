package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class commendMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commend_main);

        ImageButton 通_通知_IB=findViewById(R.id.通_通知_IB);
        通_通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(commendMain.this,noti.class);
                startActivity(intent);
            }
        });
        ImageButton 通_個人_IB=findViewById(R.id.通_個人_IB);
        通_個人_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(commendMain.this,Personal.class);
                startActivity(intent);
            }
        });


        ImageButton 飲_居家檢測_IB=findViewById(R.id.飲_居家檢測_IB);
        飲_居家檢測_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(commendMain.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton 飲_醫療小卡_IB=findViewById(R.id.飲_醫療小卡_IB);
        飲_醫療小卡_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(commendMain.this,MedicalCard.class);
                startActivity(intent);
            }
        });
        ImageButton 飲_衛教資訊_IB=findViewById(R.id.飲_衛教資訊_IB);
        飲_衛教資訊_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(commendMain.this,edu_main.class);
                startActivity(intent);
            }
        });
        ImageButton 飲_飲食推薦_IB=findViewById(R.id.飲_飲食推薦_IB);
        飲_飲食推薦_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(commendMain.this,commendMain.class);
                startActivity(intent);
            }
        });

    }
}