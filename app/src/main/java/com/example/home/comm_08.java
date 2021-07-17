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

public class comm_08 extends AppCompatActivity {
    TextView 飲_癌症內文_TV, textView15, textView16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_08);

 /*       ImageButton 通_通知_IB=findViewById(R.id.通_通知_IB);
        通_通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_08.this,noti.class);
                startActivity(intent);
            }
        });

  */
        ImageButton 通_個人_IB=findViewById(R.id.通_個人_IB);
        通_個人_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_08.this,Personal.class);
                startActivity(intent);
            }
        });
        ImageButton 通_首頁_IB=findViewById(R.id.通_首頁_IB);
        通_首頁_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_08.this,Homepage.class);
                startActivity(intent);
            }
        });
        ImageButton 居_IB=findViewById(R.id.居_IB);
        居_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_08.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton 醫_IB=findViewById(R.id.醫_IB);
        醫_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_08.this,MedicalCard.class);
                startActivity(intent);
            }
        });
        /*ImageButton 預_IB=findViewById(R.id.預_IB);
        預_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_01.this,noti.class);
                startActivity(intent);
            }
        });*/
        ImageButton 即_IB=findViewById(R.id.即_IB);
        即_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_08.this,CurrentLocation.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_IB=findViewById(R.id.衛_IB);
        衛_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_08.this,edu_main.class);
                startActivity(intent);
            }
        });
        ImageButton 飲_IB=findViewById(R.id.飲_IB);
        飲_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_08.this,commendMain.class);
                startActivity(intent);
            }
        });

        飲_癌症內文_TV = findViewById(R.id.飲_癌症內文_TV);
        // 資料若超過頁面，須設定可以Scrolling
        飲_癌症內文_TV.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 讀取 raw folder 的檔案
        InputStreamReader isr = new InputStreamReader(this.getResources().openRawResource(R.raw.food_big));
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

        飲_癌症內文_TV.setText(sb.toString());

        textView15 = findViewById(R.id.textView15);
        // 資料若超過頁面，須設定可以Scrolling
        textView15.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 讀取 raw folder 的檔案
        InputStreamReader isr_1 = new InputStreamReader(this.getResources().openRawResource(R.raw.food_lung));
        BufferedReader br_1 = new BufferedReader(isr_1);
        StringBuilder sb_1 = new StringBuilder();
        String linee;
        try {
            while ((linee = br_1.readLine()) != null) {
                sb_1.append(linee);
                sb_1.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        textView15.setText(sb_1.toString());

        textView16 = findViewById(R.id.textView16);
        // 資料若超過頁面，須設定可以Scrolling
        textView16.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 讀取 raw folder 的檔案
        InputStreamReader isr_2 = new InputStreamReader(this.getResources().openRawResource(R.raw.food_milk));
        BufferedReader br_2 = new BufferedReader(isr_2);
        StringBuilder sb_2 = new StringBuilder();
        String lineee;
        try {
            while ((lineee = br_2.readLine()) != null) {
                sb_2.append(lineee);
                sb_2.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        textView16.setText(sb_2.toString());
    }
}