package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class edu_05 extends AppCompatActivity {
    TextView 衛_腎臟疾病內文_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_05);


/*        ImageButton 通_通知_IB=findViewById(R.id.通_通知_IB);
        通_通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_05.this,noti.class);
                startActivity(intent);
            }
        });

 */
        ImageButton 通_個人_IB=findViewById(R.id.通_個人_IB);
        通_個人_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_05.this,Personal.class);
                startActivity(intent);
            }
        });
        ImageButton 通_首頁_IB=findViewById(R.id.通_首頁_IB);
        通_首頁_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_05.this,Homepage.class);
                startActivity(intent);
            }
        });

        ImageButton 衛_居家檢測_IB=findViewById(R.id.衛_居家檢測_IB);
        衛_居家檢測_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_05.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_醫療小卡_IB=findViewById(R.id.衛_醫療小卡_IB);
        衛_醫療小卡_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_05.this,MedicalCard.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_衛教資訊_IB=findViewById(R.id.衛_衛教資訊_IB);
        衛_衛教資訊_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_05.this,edu_main.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_飲食推薦_IB=findViewById(R.id.衛_飲食推薦_IB);
        衛_飲食推薦_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_05.this,commendMain.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_即時定位_IB=findViewById(R.id.衛_即時定位_IB);
        衛_即時定位_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_05.this,CurrentLocation.class);
                startActivity(intent);
            }
        });


        衛_腎臟疾病內文_TV = findViewById(R.id.衛_腎臟疾病內文_TV);
        // 資料若超過頁面，須設定可以Scrolling
        衛_腎臟疾病內文_TV.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 讀取 raw folder 的檔案
        InputStreamReader isr = new InputStreamReader(this.getResources().openRawResource(R.raw.kidney));
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        衛_腎臟疾病內文_TV.setText(sb.toString());
    }
}