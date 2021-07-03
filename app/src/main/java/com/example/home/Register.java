package com.example.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

//import com.example.home.databinding.ActivityMainBinding;

public class Register extends AppCompatActivity {

    //biewbinding
    //private ActivityMainBinding binding;
    //if code send failed, will used to resend code OTP
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId; // will hold OTP/Verification code
    private Activity context = this;
    private EditText ePassword, eCheck, eEmail, ePhone, eSend;
    private Button eFinish, eContinue, eResend, eCodeSubmit;
    private String email;

    private FirebaseAuth mAuth;
    private static final String TAG ="MAIN_TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        ePassword = findViewById(R.id.et_password_register);
        eCheck = findViewById(R.id.et_check_register);
        eEmail = findViewById(R.id.et_email_register);
        ePhone = findViewById(R.id.et_phone_register);
        eFinish = findViewById(R.id.btn_finfsh_register);
        eSend = findViewById(R.id.et_send_register);
        eContinue = findViewById(R.id.btn_Continue_register);
        eResend = findViewById(R.id.btn_Resend_register);
        eCodeSubmit=findViewById(R.id.btn_codeSubmit_register);


        Button btn_finfsh_register=findViewById(R.id.btn_finfsh_register);
        btn_finfsh_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,Homepage.class);
                startActivity(intent);
            }
        });

        mCallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Toast.makeText(Register.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(verificationId, forceResendingToken);
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent: "+verificationId);

                mVerificationId=verificationId;
                forceResendingToken = token;
                // hide phone layout, show code layout

            }
        };

        //eContinue click: input phone number, validate,start phone authentication/login
        eContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = ePhone.getText().toString().trim();
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(Register.this,"請輸入正確的手機號碼",Toast.LENGTH_SHORT).show();
                }
                else{
                    startPhoneNumberVerification(phone);
                }
            }
        });

        //eResend click:(if code didn't receiver) resend verification code /OTP
        eResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = ePhone.getText().toString().trim();
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(Register.this,"請輸入正確的手機號碼",Toast.LENGTH_SHORT).show();
                }
                else{
                    resendVerificationCode(phone, forceResendingToken);
                }
            }
        });

        //eCodeSubmit click:input verification code, validate, verify phone number with verification code
        eCodeSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = eSend.getText().toString().trim();
                if(TextUtils.isEmpty(code)){
                    Toast.makeText(Register.this,"請輸入正確的驗證碼",Toast.LENGTH_SHORT).show();
                }
                else{
                    verifyPhoneNumberWithCode(mVerificationId, code);
                }
            }
        });
        eFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = ePassword.getText().toString();
                String Check = eCheck.getText().toString();
                String Email = eEmail.getText().toString();
                String Phone = ePhone.getText().toString();
                String send=eSend.getText().toString();
                //驗證碼還沒做
                mAuth.createUserWithEmailAndPassword(eEmail.getText().toString(), ePassword.getText().toString()).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(TextUtils.isEmpty(Email)){
                            eEmail.setError("請輸入email");
                            return;
                        }
                        else if(TextUtils.isEmpty(password)){
                            ePassword.setError("請輸入密碼");
                            return;
                        }
                        else if(TextUtils.isEmpty(Check)){
                            ePassword.setError("請輸入密碼");
                            return;
                        }
                        else if(!password.equals(Check)){
                            ePassword.setError("請輸入相同密碼");
                            return;
                        }
                        else if(password.length()<4){
                            ePassword.setError("密碼請大於4個字元");
                            return;
                        }
                        else if(!isVallidEmail(Email)){
                            eEmail.setError("無效的email");
                            return;
                        }
                        else if(TextUtils.isEmpty(Phone)){
                            ePhone.setError("請輸入手機號碼");
                            return;
                        }
                        else if(TextUtils.isEmpty(send)){
                            eSend.setError("請輸入驗證碼");
                            return;
                        }
                        Intent intent = new Intent(Register.this, Signin.class);
                        startActivity(intent);  //回到登入
                    }
                });
            }
        });

    }


    private void startPhoneNumberVerification(String phone) {
        PhoneAuthOptions options=
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone) //Phone number,must be with country code for example for Taiwan +886
                        .setTimeout(60L, TimeUnit.SECONDS) //The timeout and unit
                        .setActivity(this) //Activity (for callback binding)
                        .setCallbacks(mCallbacks) // OnVerificationsStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void resendVerificationCode(String phone, PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthOptions options=
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone) //Phone number,must be with country code for example for Taiwan +886
                        .setTimeout(60L, TimeUnit.SECONDS) //The timeout and unit
                        .setActivity(this) //Activity (for callback binding)
                        .setCallbacks(mCallbacks) // OnVerificationsStateChangedCallbacks
                        .setForceResendingToken(token)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // successfully signed in
                        String phone = mAuth.getCurrentUser().getPhoneNumber();
                        Toast.makeText(Register.this,"登入成功"+phone, Toast.LENGTH_SHORT).show();

                        //start profile activity
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failed signing in
                        Toast.makeText(Register.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private Boolean isVallidEmail(CharSequence target){
        return(!TextUtils.isEmpty(target)&& Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
