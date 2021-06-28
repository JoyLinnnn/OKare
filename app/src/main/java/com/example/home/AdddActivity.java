package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdddActivity extends AppCompatActivity {
    EditText edTime, edHint;
    Button btn_sureadd;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addd);

        Button 確定新增=findViewById(R.id.確定新增);
        確定新增.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdddActivity.this,Doctor.class);
                startActivity(intent);
            }
        });


        edTime = findViewById(R.id.edTime);
        edHint = findViewById(R.id.edHint);
        確定新增 = findViewById(R.id.確定新增);
        確定新增.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTime = edTime.getText().toString();
                String getHint = edHint.getText().toString();

                if (getTime.isEmpty()){
                    edTime.setError("請輸入回診日期及時間...");
                }else if (getHint.isEmpty()){
                    edHint.setError("請輸入醫院門診及編號...");
                }else{
                    database.child("Users").push().setValue(new ModelUsers(getTime, getHint)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AdddActivity.this, "回診資料新增成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AdddActivity.this, Doctor.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdddActivity.this, "回診資料新增失敗！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}