package com.example.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {

    public static String PREFS_NAME="MyPrefsFile";
    private EditText ePhone, ePassword, eEmail;
    private Button eLogin;
    private ImageButton 註冊_IB;
    private TextView ekeepLogin, etoRegister, etoForgot;
    private FirebaseAuth mAuth;
    private String Username="Sunny",
            Password="123123";
    boolean isValid=false;
    private int counter=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        註冊_IB = findViewById(R.id.註冊_IB);
        eEmail = findViewById(R.id.et_email);
        ePassword = findViewById(R.id.et_password);
        eLogin = findViewById(R.id.btn_login);
   //     ekeepLogin = findViewById(R.id.tv_keeplogin);
        etoRegister = findViewById(R.id.tv_toregister);
        etoForgot = findViewById(R.id.tv_toforgotpassword);
        mAuth=FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(Signin.this, Homepage.class));
            finish();
        }

        ImageButton 忘記_IB=findViewById(R.id.忘記_IB);
        忘記_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signin.this,ForgetPSWD.class);
                startActivity(intent);
            }
        });

        註冊_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signin.this, Register.class);
                startActivity(intent);
            }
        });
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=eEmail.getText().toString();
                String password=ePassword.getText().toString();
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(Signin.this,"all fields required",Toast.LENGTH_SHORT).show();
                }else{
                    login(email,password);
                }
            }
        });
        /*eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });*/


    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signin.this,"登入成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signin.this, Homepage.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(Signin.this,"登入失敗",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validate(String name, String password){
        if(name.equals(Username) && password.equals(Password)){
            return true;
        }
        return  false;
    }
    /*private void Login(){
        String email=eEmail.getText().toString();
        String password=ePassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            eEmail.setError("輸入email");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            ePassword.setError("輸入密碼");
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser user=mAuth.getCurrentUser();
                            Toast.makeText(Signin.this,"登入成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signin.this, Homepage.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(Signin.this,"登入失敗",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }*/
}