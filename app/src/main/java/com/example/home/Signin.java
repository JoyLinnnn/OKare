package com.example.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signin extends AppCompatActivity {

    private EditText ePhone, ePassword, eEmail;
    private Button eLogin;
    private TextView ekeepLogin, etoRegister, etoForgot;

    private String Username="Sunny",
            Password="123123";

    boolean isValid=false;
    private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ePhone=findViewById(R.id.et_phone);
        eEmail=findViewById(R.id.et_email);
        ePassword=findViewById(R.id.et_password);
        eLogin=findViewById(R.id.btn_login);
        ekeepLogin=findViewById(R.id.tv_keeplogin);
        etoRegister=findViewById(R.id.tv_toregister);
        etoForgot=findViewById(R.id.tv_toforgotpassword);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputPhone=ePhone.getText().toString();
                String inputEmail=eEmail.getText().toString();
                String inputPassword=ePassword.getText().toString();

                if(inputEmail.isEmpty() || inputPassword.isEmpty()){     //判斷帳號密碼是否為空

                    Toast.makeText(Signin.this,"請輸入帳號或密碼",Toast.LENGTH_SHORT).show();

                }else{

                    isValid=validate(inputEmail,inputPassword);

                    if(!isValid){

                        counter--;

                        Toast.makeText(Signin.this,"帳號或密碼錯誤",Toast.LENGTH_SHORT).show();

                        if(counter==0){
                            eLogin.setEnabled(false);
                        }
                    }else{

                        Toast.makeText(Signin.this,"成功登入",Toast.LENGTH_SHORT).show();

                        //跳至主頁

                        Intent intent=new Intent(Signin.this,Homepage.class);
                        startActivity(intent);
                    }
                }
            }
        });

       /* etoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });*/
    }
    private boolean validate(String name, String password){

        if(name.equals(Username) && password.equals(Password)){
            return true;
        }
        return  false;

    }
}