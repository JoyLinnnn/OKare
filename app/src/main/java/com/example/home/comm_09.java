package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class comm_09 extends AppCompatActivity {
    TextView 飲_過敏內文_TV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_09);


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
    }
}