package com.example.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.Objects;

public class ForgetPSWD extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailAddress;
    private Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_p_s_w_d);
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        mAuth=FirebaseAuth.getInstance();
        emailAddress=findViewById(R.id.editTextTextPersonName);
        send=findViewById(R.id.forget_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.fetchSignInMethodsForEmail(emailAddress.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if(task.getResult().getSignInMethods().isEmpty()){
                            Toast.makeText(ForgetPSWD.this,"Email尚未註冊",Toast.LENGTH_SHORT).show();
                        }else{
                            mAuth.sendPasswordResetEmail(emailAddress.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ForgetPSWD.this,"重設密碼信件已到您的信箱",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(ForgetPSWD.this, Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });

    }
}