package com.example.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Personal extends AppCompatActivity {
    private Context context = this;
    private ImageButton mFinish,mSignout;
    private ImageButton 通_通知_IB,通_個人_IB;
    private EditText mName, mPassword, mPhone;
    private TextView 帳戶名稱;
    private FirebaseFirestore db;
    FirebaseAuth mAuth;
    private String x_last = "0";
    private String x_select = "0";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        mFinish = findViewById(R.id.個_完成按鈕_IB);
        通_通知_IB = findViewById(R.id.通_通知_IB);
        通_個人_IB = findViewById(R.id.通_個人_IB);
        mName = findViewById(R.id.個_帳號輸入_ET);
        mPassword = findViewById(R.id.個_密碼輸入_ET);
        mPhone = findViewById(R.id.個_手機輸入_ET);
  //       帳戶名稱=findViewById(R.id.個_上方帳戶名稱_TV);
        mSignout=findViewById(R.id.個_編輯按紐_IB);

        db = FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> text_data = new HashMap<>();
                text_data.put("姓名", mName.getText().toString());
                text_data.put("密碼", mPassword.getText().toString());
                text_data.put("手機", mPhone.getText().toString());
                帳戶名稱.setText(mName.getText().toString().trim());
                Integer x_id = Integer.valueOf(x_last) + 1;
                db.collection("Personal").document(String.valueOf(x_id)).set(text_data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Personal.this, "新增成功", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Personal.this, "新增失敗", Toast.LENGTH_SHORT).show();
                    }
                });
                data_select();
            }
        });



        mSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                signOutUser();
            }
        });


        通_通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personal.this, noti.class);
                startActivity(intent);
            }
        });

        通_個人_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personal.this, Personal.class);
                startActivity(intent);
            }
        });
        ImageButton 通_首頁_IB=findViewById(R.id.通_首頁_IB);
        通_首頁_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Personal.this,Homepage.class);
                startActivity(intent);
            }
        });
    }

    private void signOutUser() {
        Intent mainActivity =new Intent(Personal.this,Signin.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivity);
        finish();
    }


    private void data_select() {
        CollectionReference CR = db.collection("Personal");
        final List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        CR.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", document.getId());
                    item.put("帳號", document.get("帳號"));
                    item.put("密碼", document.get("密碼"));
                    item.put("手機", document.get("手機"));
                    items.add(item);
                    x_last = document.getId();
                }
            }
        });
    }
}