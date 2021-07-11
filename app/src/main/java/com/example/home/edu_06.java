package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class edu_06 extends AppCompatActivity {
    TextView 衛_新冠肺炎內文_TV, textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_06);


        ImageButton 通_通知_IB=findViewById(R.id.通_通知_IB);
        通_通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_06.this,noti.class);
                startActivity(intent);
            }
        });
        ImageButton 通_個人_IB=findViewById(R.id.通_個人_IB);
        通_個人_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_06.this,Personal.class);
                startActivity(intent);
            }
        });
        ImageButton 通_首頁_IB=findViewById(R.id.通_首頁_IB);
        通_首頁_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_06.this,Homepage.class);
                startActivity(intent);
            }
        });

        ImageButton 衛_居家檢測_IB=findViewById(R.id.衛_居家檢測_IB);
        衛_居家檢測_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_06.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_醫療小卡_IB=findViewById(R.id.衛_醫療小卡_IB);
        衛_醫療小卡_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_06.this,MedicalCard.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_衛教資訊_IB=findViewById(R.id.衛_衛教資訊_IB);
        衛_衛教資訊_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_06.this,edu_main.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_飲食推薦_IB=findViewById(R.id.衛_飲食推薦_IB);
        衛_飲食推薦_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_06.this,commendMain.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_即時定位_IB=findViewById(R.id.衛_即時定位_IB);
        衛_即時定位_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edu_06.this,CurrentLocation.class);
                startActivity(intent);
            }
        });

        衛_新冠肺炎內文_TV = findViewById(R.id.衛_新冠肺炎內文_TV);
        // 資料若超過頁面，須設定可以Scrolling
        衛_新冠肺炎內文_TV.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 讀取 raw folder 的檔案
        InputStreamReader isr = new InputStreamReader(this.getResources().openRawResource(R.raw.covid));
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

        衛_新冠肺炎內文_TV.setText(sb.toString());

        textView = (TextView) findViewById(R.id.衛_新冠超連結_TV);
        String webLinkText = "查看附近施打疫苗診所" ;
        SpannableString 衛_新冠超連結_TV = new SpannableString(webLinkText);
        NoUnderlineSpan mNoUnderlineSpan = new NoUnderlineSpan("https://antiflu.cdc.gov.tw/Covid19") ;
        衛_新冠超連結_TV.setSpan(mNoUnderlineSpan,0,衛_新冠超連結_TV.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(衛_新冠超連結_TV);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    public static class NoUnderlineSpan extends URLSpan {
        public NoUnderlineSpan(String url) {
            super(url);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
        }
    }
}