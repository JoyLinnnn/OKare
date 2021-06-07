package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.home.R.id.居_衛教資訊_IB;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mydb;
    TextView temp,hum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp=(TextView)findViewById(R.id.temp);
        hum=(TextView)findViewById(R.id.hum);
        mydb= FirebaseDatabase.getInstance().getReference().child("DHT11SensorData");
        try {

            mydb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String humdata = dataSnapshot.child("Humidity").getValue().toString();
                    String tempdata = dataSnapshot.child("Temperature").getValue().toString();
                    hum.setText(humdata);
                    temp.setText(tempdata);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                }
            });
        } catch(Exception e)
        {

        }

        ImageButton 居_衛教資訊_IB=findViewById(R.id.居_衛教資訊_IB);
        居_衛教資訊_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,edu_main.class);
                startActivity(intent);
            }
        });

        ImageButton _通知_IB=findViewById(R.id._通知_IB);
        _通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,noti.class);
                startActivity(intent);
            }
        });



    }
}