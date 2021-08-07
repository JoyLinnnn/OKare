package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import android.view.View;
import android.widget.ImageButton;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class comm_09 extends AppCompatActivity {
    TextView 飲_過敏內文_TV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_09);

        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        飲_過敏內文_TV = findViewById(R.id.飲_過敏內文_TV);
        // 資料若超過頁面，須設定可以Scrolling
        飲_過敏內文_TV.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 讀取 raw folder 的檔案
        InputStreamReader isr = new InputStreamReader(this.getResources().openRawResource(R.raw.food_nose));
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

        飲_過敏內文_TV.setText(sb.toString());

/*        ImageButton 通_通知_IB=findViewById(R.id.通_通知_IB);
        通_通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_09.this,noti.class);
                startActivity(intent);
            }
        });

 */
        ImageButton 通_個人_IB=findViewById(R.id.通_個人_IB);
        通_個人_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_09.this,Personal.class);
                startActivity(intent);
            }
        });
        ImageButton 通_首頁_IB=findViewById(R.id.通_首頁_IB);
        通_首頁_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_09.this,Homepage.class);
                startActivity(intent);
            }
        });
        ImageButton 居_IB=findViewById(R.id.居_IB);
        居_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_09.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton 醫_IB=findViewById(R.id.醫_IB);
        醫_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_09.this,MedicalCard.class);
                startActivity(intent);
            }
        });
        ImageButton 預_IB=findViewById(R.id.預_IB);
        預_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_09.this,ReserveHospital.class);
                startActivity(intent);
            }
        });
        ImageButton 即_IB=findViewById(R.id.即_IB);
        即_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_09.this,CurrentLocation.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_IB=findViewById(R.id.衛_IB);
        衛_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_09.this,edu_main.class);
                startActivity(intent);
            }
        });
        ImageButton 飲_IB=findViewById(R.id.飲_IB);
        飲_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_09.this,commendMain.class);
                startActivity(intent);
            }
        });

    }
}