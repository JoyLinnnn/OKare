package com.example.home;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.util.NumberUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.Calendar;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class MainActivity extends AppCompatActivity {
    DatabaseReference mydb;
    TextView HR,SpO2,temp,hum;

    //1013跳通知
    NotificationManager notificationManager;
    NotificationChannel channel;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
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


        //1013 跳通知
        notificationManager=(NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
        channel=new NotificationChannel("ID","home",NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(channel);

        double avg=0.0;
        double curTemp=0.0;
        double ans=0;
        String s="";

        //取得目前月份
        Calendar mCal = Calendar.getInstance();
        CharSequence getMonth = DateFormat.format("MM", mCal.getTime());    // kk:24小時制, hh:12小時制
        String mm=(String)getMonth;
        int m=Integer.parseInt(mm);

        //計算當月平均
        avg=Double.parseDouble(getMonthAverage(m));
        //取得目前溫度
        curTemp=Double.parseDouble(temp.getText().toString());
        //計算溫差
        ans=compute(curTemp,avg);
        //訊息字串
        s=printString(ans);

        //通知
        Notification.Builder builder=new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setChannelId("ID")
                .setContentTitle("歷年"+m+"月均溫: "+avg)
                .setContentText(s);

        Notification notification=builder.build();
        notificationManager.notify(0,notification);

    }

    private String printString(double ans) {
        String s;
        if(ans>5){
            s="溫差為 "+ans+" 度，注意高溫";
            return s;
        }else if(ans<-5){
            s="溫差為 "+ans+" 度，注意低溫";
            return s;
        }else{
            s="溫差為 "+ans+" 度";
            return s;
        }
    }

    private double compute(double curTemp, double avg) {
        return Math.round((curTemp-avg)*100.0)/100.0;
    }


    private String getMonthAverage(int m) {
        //讀excel
        Double avg =0.0;
        String ans="";
        int t=0;

        try {

            AssetManager am = getAssets();
            InputStream is = am.open("各月份氣溫濕度.xls");
            Workbook wb = Workbook.getWorkbook(is);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();

            //取得每年的月份
            for (int i = m, j = 0; i < row; i = i + 12, j++) {
                Cell z = s.getCell(1, i);
                avg=avg+Double.parseDouble(z.getContents());
                t=j;
            }
            avg=Math.round((avg/t)*100.0)/100.0;
            ans=String.valueOf(avg);

        } catch (Exception e) {

        }

        return ans;

    }

}