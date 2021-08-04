package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class Webview extends AppCompatActivity {
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        web=findViewById(R.id.webView);
        WebSettings webSettings=web.getSettings();
        ((WebSettings) webSettings).setJavaScriptEnabled(true);
        web.setWebViewClient(new Callback());
        web.loadUrl("http://192.168.35.52/cam.mjpeg");

        ImageButton 居_居家檢測_IB=findViewById(R.id.居_居家檢測_IB);
        居_居家檢測_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Webview.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton 居_衛教資訊_IB=findViewById(R.id.居_衛教資訊_IB);
        居_衛教資訊_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Webview.this,edu_main.class);
                startActivity(intent);
            }
        });
        ImageButton 居_醫療小卡_IB=findViewById(R.id.居_醫療小卡_IB);
        居_醫療小卡_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Webview.this,MedicalCard.class);
                startActivity(intent);
            }
        });
        ImageButton 居_飲食推薦_IB=findViewById(R.id.居_飲食推薦_IB);
        居_飲食推薦_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Webview.this,commendMain.class);
                startActivity(intent);
            }
        });



 /*       ImageButton _通知_IB=findViewById(R.id._通知_IB);
        _通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Webview.this,noti.class);
                startActivity(intent);
            }
        });

  */
        ImageButton _個人_IB=findViewById(R.id._個人_IB);
        _個人_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Webview.this,Personal.class);
                startActivity(intent);
            }
        });
        ImageButton _首頁_IB=findViewById(R.id._首頁_IB);
        _首頁_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Webview.this,Homepage.class);
                startActivity(intent);
            }
        });


    }
    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}