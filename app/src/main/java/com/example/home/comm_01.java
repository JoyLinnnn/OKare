package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class comm_01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_01);

        ImageButton 通_通知_IB=findViewById(R.id.通_通知_IB);
        通_通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_01.this,noti.class);
                startActivity(intent);
            }
        });
        ImageButton 通_個人_IB=findViewById(R.id.通_個人_IB);
        通_個人_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_01.this,Personal.class);
                startActivity(intent);
            }
        });
        ImageButton 居_IB=findViewById(R.id.居_IB);
        居_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_01.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton 醫_IB=findViewById(R.id.醫_IB);
        醫_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_01.this,MedicalCard.class);
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
        });
        ImageButton 即_IB=findViewById(R.id.即_IB);
        即_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_01.this,noti.class);
                startActivity(intent);
            }
        });*/
        ImageButton 衛_IB=findViewById(R.id.衛_IB);
        衛_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_01.this,edu_main.class);
                startActivity(intent);
            }
        });
        ImageButton 飲_IB=findViewById(R.id.飲_IB);
        飲_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(comm_01.this,commendMain.class);
                startActivity(intent);
            }
        });
    }
}