package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicalCard extends AppCompatActivity {
    private Context context=this;
    private ImageButton mFinish;
    private ImageButton mModify, 提醒事項_IB;
    private EditText mName, mGender, mBloodtype, mAddress, mAllergy, mHistory, mFamilyname1, mFamilyname2, mFamilyphone1, mFamilyphone2;
    private FirebaseFirestore db;
    private String x_last="0";
    private String x_select="0";
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_card);
        提醒事項_IB=findViewById(R.id.提醒事項_IB);
        mFinish = findViewById(R.id.醫卡_完成按鈕_IB);
        mModify = findViewById(R.id.醫卡_編輯按鈕_IB);
        mName = findViewById(R.id.醫卡_姓名輸入_ET);
        mGender = findViewById(R.id.醫卡_性別輸入_ET);
        mBloodtype = findViewById(R.id.醫卡_血型輸入_ET);
        mAddress = findViewById(R.id.醫卡_住址輸入_ET);
        mAllergy = findViewById(R.id.醫卡_藥物過敏輸入_ET);
        mHistory = findViewById(R.id.醫卡_病史輸入_ET);
        mFamilyname1 = findViewById(R.id.醫卡_聯絡人1姓名輸入_ET);
        mFamilyphone1 = findViewById(R.id.醫卡_聯絡人1電話輸入_ET);
        mFamilyname2 = findViewById(R.id.醫卡_聯絡人2姓名輸入_ET);
        mFamilyphone2 = findViewById(R.id.醫卡_聯絡人2電話輸入_ET);

        db = FirebaseFirestore.getInstance();
        //新增
        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> text_data = new HashMap<>();
                text_data.put("姓名", mName.getText().toString());
                text_data.put("性別", mGender.getText().toString());
                text_data.put("血型",mBloodtype.getText().toString());
                text_data.put("地址", mAddress.getText().toString());
                text_data.put("藥物過敏", mAllergy.getText().toString());
                text_data.put("病史", mHistory.getText().toString());
                text_data.put("緊急聯絡人姓名1", mFamilyname1.getText().toString());
                text_data.put("緊急聯絡人電話1", mFamilyphone1.getText().toString());
                text_data.put("緊急聯絡人姓名2", mFamilyname2.getText().toString());
                text_data.put("緊急聯絡人電話2", mFamilyphone2.getText().toString());

                Integer x_id = Integer.valueOf(x_last) + 1;
                db.collection("users").document(String.valueOf(x_id)).set(text_data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MedicalCard.this, "新增成功", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MedicalCard.this, "新增失敗", Toast.LENGTH_SHORT).show();
                    }
                });
                data_select();
            }
        });
        //修改
        /*mModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> text_data = new HashMap<>();
                text_data.put("姓名", mName.getText().toString());
                text_data.put("性別", mGender.getText().toString());

                db.collection("users").document(x_select).update(text_data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MedicalCard.this, "修改成功", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MedicalCard.this, "修改失敗", Toast.LENGTH_SHORT).show();
                    }
                });
                data_select();
            }
        });*/
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

        ImageButton 通_通知_IB=findViewById(R.id.通_通知_IB);
        通_通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MedicalCard.this,noti.class);
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


    }

    private void data_select(){
        CollectionReference CR=db.collection("users");
        final List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();
        CR.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot document:task.getResult()){
                    Map<String,Object> item=new HashMap<String, Object>();
                    item.put("id",document.getId());
                    item.put("姓名",document.get("姓名"));
                    item.put("性別",document.get("性別"));
                    item.put("血型",document.get("血型"));
                    item.put("地址",document.get("地址"));
                    item.put("藥物過敏",document.get("藥物過敏"));
                    item.put("病史",document.get("病史"));
                    item.put("緊急聯絡人姓名1",document.get("緊急聯絡人姓名1"));
                    item.put("緊急聯絡人電話1",document.get("緊急聯絡人電話1"));
                    item.put("緊急聯絡人姓名2",document.get("緊急聯絡人姓名2"));
                    item.put("緊急聯絡人電話2",document.get("緊急聯絡人電話2"));
                    items.add(item);
                    x_last=document.getId();
                }
            }
        });
    }
}
