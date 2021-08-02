package com.example.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mydb;
    TextView HR,SpO2,temp,hum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HR=(TextView)findViewById(R.id.HR);
        SpO2=(TextView)findViewById(R.id.SpO2);
        temp=(TextView)findViewById(R.id.temp);
        hum=(TextView)findViewById(R.id.hum);
        mydb= FirebaseDatabase.getInstance().getReference().child("Dato");
        try {

            mydb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String humdata = dataSnapshot.child("hum").getValue().toString();
                    String tempdata = dataSnapshot.child("temp").getValue().toString();
                    String HRdata = dataSnapshot.child("Heart").getValue().toString();
                    String SpO2data = dataSnapshot.child("SpO2").getValue().toString();
                    hum.setText(humdata);
                    temp.setText(tempdata);
                    HR.setText(HRdata);
                    SpO2.setText(SpO2data);
 //                   String HRdata=HR.getText().toString();
 //                   String SpO2data=SpO2.getText().toString();
 //                   String humdata=hum.getText().toString();//
 //                   String tempdata=temp.getText().toString();
 //                   HashMap<String,String> userMap=new HashMap<>();
//                    userMap.put("HR",HRdata);
//                   userMap.put("SpO2",SpO2data);
//                    userMap.put("Humidity",humdata);
//                    userMap.put("Temperature",tempdata);
//                    mydb.setValue(userMap);
                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                }
            });
        } catch(Exception e)
        {

        }
        ImageButton 居_居家檢測_IB=findViewById(R.id.居_居家檢測_IB);
        居_居家檢測_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton 居_衛教資訊_IB=findViewById(R.id.居_衛教資訊_IB);
        居_衛教資訊_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,edu_main.class);
                startActivity(intent);
            }
        });
        ImageButton 居_即時定位_IB=findViewById(R.id.居_即時定位_IB);
        居_即時定位_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CurrentLocation.class);
                startActivity(intent);
            }
        });
        ImageButton 居_醫療小卡_IB=findViewById(R.id.居_醫療小卡_IB);
        居_醫療小卡_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MedicalCard.class);
                startActivity(intent);
            }
        });
        ImageButton 居_飲食推薦_IB=findViewById(R.id.居_飲食推薦_IB);
        居_飲食推薦_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,commendMain.class);
                startActivity(intent);
            }
        });
        ImageButton 居_預約掛號_IB=findViewById(R.id.居_預約掛號_IB);
        居_預約掛號_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ReserveHospital.class);
                startActivity(intent);
            }
        });




/*        ImageButton _通知_IB=findViewById(R.id._通知_IB);
        _通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,noti.class);
                startActivity(intent);
            }
        });

 */
        ImageButton _個人_IB=findViewById(R.id._個人_IB);
        _個人_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Personal.class);
                startActivity(intent);
            }
        });
        ImageButton _首頁_IB=findViewById(R.id._首頁_IB);
        _首頁_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Homepage.class);
                startActivity(intent);
            }
        });
        ImageButton 點擊查看_IB=findViewById(R.id.點擊查看_IB);
        點擊查看_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Webview.class);
                startActivity(intent);
            }
        });


        }


    }