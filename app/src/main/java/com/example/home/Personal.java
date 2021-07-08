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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Personal extends AppCompatActivity {
    private Context context = this;
    private ImageButton mFinish,mSignout;
    private ImageButton 通_通知_IB,通_個人_IB;
    private EditText mName, mPassword, mPhone;
    private TextView 帳戶名稱;
    private FirebaseFirestore db;
    private String x_last = "0";
    private String x_select = "0";
    DatabaseReference root;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

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
        mSignout=findViewById(R.id.個_編輯按紐_IB);
        db = FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();

        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mName.getText().toString();
                String password=mPassword.getText().toString();
                String phone=mPhone.getText().toString();
                if(name.isEmpty()||password.isEmpty()||phone.isEmpty()){
                    Toast.makeText(Personal.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }else if(!phone.equals(password)){
                    Toast.makeText(Personal.this,"確認密碼相同",Toast.LENGTH_SHORT).show();
                }
                else{
                    changePassword(name,password);
                }
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

    private void changePassword(String name, String password) {
        AuthCredential credential= EmailAuthProvider.getCredential(firebaseUser.getEmail(),name);
        firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    firebaseUser.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                mAuth.signOut();
                                Intent intent = new Intent(Personal.this,Signin.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Personal.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(Personal.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signOutUser() {
        Intent mainActivity =new Intent(Personal.this,Signin.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivity);
        finish();
    }


    /*private void data_select() {
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
    }*/
}