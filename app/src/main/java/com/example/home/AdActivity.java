package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdActivity extends AppCompatActivity {
    EditText edMT, edMN;
    Button btn_suadd;
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        Button 確定新增=findViewById(R.id.確定新增);
        確定新增.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdActivity.this,Medicine.class);
                startActivity(intent);
            }
        });


        edMT = findViewById(R.id.edMT);
        edMN = findViewById(R.id.edMN);
        確定新增 = findViewById(R.id.確定新增);
        確定新增.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getMT = edMT.getText().toString();
                String getMN = edMN.getText().toString();

                if (getMT.isEmpty()){
                    edMT.setError("請輸入日期...");
                }else if (getMN.isEmpty()){
                    edMN.setError("請輸入藥品數字...");
                }else{
                    data.child("Medicine").push().setValue(new ModelMedicine(getMT, getMN)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AdActivity.this, "終於成功ㄌ! ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AdActivity.this, Medicine.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdActivity.this, "噢不又失敗! ", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}