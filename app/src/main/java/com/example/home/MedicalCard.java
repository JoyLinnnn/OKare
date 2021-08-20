package com.example.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MedicalCard extends AppCompatActivity {
    private Context context=this;
    private ImageButton mFinish,提醒事項_IB,load;
    private TextView 醫卡tv_姓名,醫卡tv_性別,醫卡tv_血型,醫卡tv_住址,醫卡tv_藥物過敏,醫卡tv_病史,醫卡tv_緊急姓名1,醫卡tv_緊急電話1,醫卡tv_緊急姓名2,醫卡tv_緊急電話2;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private String uid;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_card);
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        提醒事項_IB=findViewById(R.id.提醒事項_IB);
        mFinish = findViewById(R.id.醫卡_完成按鈕_IB);
        load=findViewById(R.id.load);
        醫卡tv_姓名= findViewById(R.id.醫卡_姓名_tv);
        醫卡tv_性別 = findViewById(R.id.醫卡_性別_tv);
        醫卡tv_血型 = findViewById(R.id.醫卡_血型_tv);
        醫卡tv_住址 = findViewById(R.id.醫卡_住址_tv);
        醫卡tv_藥物過敏 = findViewById(R.id.醫卡_藥物過敏_tv);
        醫卡tv_病史= findViewById(R.id.醫卡_病史_tv);
        醫卡tv_緊急姓名1 = findViewById(R.id.醫卡_緊急姓名1_tv);
        醫卡tv_緊急電話1 = findViewById(R.id.醫卡_緊急電話1_tv);
        醫卡tv_緊急姓名2 = findViewById(R.id.醫卡_緊急姓名2_tv);
        醫卡tv_緊急電話2 = findViewById(R.id.醫卡_緊急電話2_tv);
        db = FirebaseFirestore.getInstance();

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchdata();
            }
        });

        /*load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("okaredb").document("Medicard").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            String 姓名=documentSnapshot.getString("姓名");
                            String 性別=documentSnapshot.getString("性別");
                            String 血型=documentSnapshot.getString("血型");
                            String 住址=documentSnapshot.getString("住址");
                            String 藥物過敏=documentSnapshot.getString("藥物過敏");
                            String 病史=documentSnapshot.getString("病史");
                            String 緊急姓名1=documentSnapshot.getString("緊急姓名1");
                            String 緊急電話1=documentSnapshot.getString("緊急電話1");
                            String 緊急姓名2=documentSnapshot.getString("緊急姓名2");
                            String 緊急電話2=documentSnapshot.getString("緊急電話2");
                            Map<String,Object> map=documentSnapshot.getData();
                            醫卡tv_姓名.setText(姓名);
                            醫卡tv_性別.setText(性別);
                            醫卡tv_血型.setText(血型);
                            醫卡tv_住址.setText(住址);
                            醫卡tv_藥物過敏.setText(藥物過敏);
                            醫卡tv_病史.setText(病史);
                            醫卡tv_緊急姓名1.setText(緊急姓名1);
                            醫卡tv_緊急電話1.setText(緊急電話1);
                            醫卡tv_緊急姓名2.setText(緊急姓名2);
                            醫卡tv_緊急電話2.setText(緊急電話2);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MedicalCard.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/

        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MedicalCard.this,MedicardWrite.class);
                startActivity(intent);
            }
        });
        提醒事項_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MedicalCard.this,Medicine.class);
                startActivity(intent);
            }
        });
        ImageButton 醫療小卡_IB=findViewById(R.id.醫療小卡_IB);
        醫療小卡_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MedicalCard.this,MedicalCard.class);
                startActivity(intent);
            }
        });
        ImageButton 居_IB=findViewById(R.id.居_IB);
        居_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MedicalCard.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton 醫_IB=findViewById(R.id.醫_IB);
        醫_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MedicalCard.this,MedicalCard.class);
                startActivity(intent);
            }
        });
        ImageButton 即_IB=findViewById(R.id.即_IB);
        即_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MedicalCard.this,CurrentLocation.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_IB=findViewById(R.id.衛_IB);
        衛_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MedicalCard.this,edu_main.class);
                startActivity(intent);
            }
        });
        ImageButton 飲_IB=findViewById(R.id.飲_IB);
        飲_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MedicalCard.this,commendMain.class);
                startActivity(intent);
            }
        });

        ImageButton 通_個人_IB=findViewById(R.id.通_個人_IB);
        通_個人_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MedicalCard.this,Personal.class);
                startActivity(intent);
            }
        });
        ImageButton 通_首頁_IB=findViewById(R.id.通_首頁_IB);
        通_首頁_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MedicalCard.this,Homepage.class);
                startActivity(intent);
            }
        });
    }
    public void fetchdata(){
        DocumentReference document=db.collection("Medicard").document("1");
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    醫卡tv_姓名.setText(documentSnapshot.getString("姓名"));
                    醫卡tv_性別.setText(documentSnapshot.getString("性別"));
                    醫卡tv_血型.setText(documentSnapshot.getString("血型"));
                    醫卡tv_住址.setText(documentSnapshot.getString("住址"));
                    醫卡tv_藥物過敏.setText(documentSnapshot.getString("藥物過敏"));
                    醫卡tv_病史.setText(documentSnapshot.getString("病史"));
                    醫卡tv_緊急姓名1.setText(documentSnapshot.getString("緊急聯絡人姓名1"));
                    醫卡tv_緊急電話1.setText(documentSnapshot.getString("緊急聯絡人電話1"));
                    醫卡tv_緊急姓名2.setText(documentSnapshot.getString("緊急聯絡人姓名2"));
                    醫卡tv_緊急電話2.setText(documentSnapshot.getString("緊急聯絡人電話2"));
                }else{
                    Toast.makeText(getApplicationContext(),"not found",Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed to fetch",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
