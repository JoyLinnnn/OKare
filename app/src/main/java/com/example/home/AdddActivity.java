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

public class AdddActivity extends AppCompatActivity {
    EditText edTime, edHint;
    Button btn_sureadd;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addd);
        edTime = findViewById(R.id.edTime);
        edHint = findViewById(R.id.edHint);
        btn_sureadd = findViewById(R.id.btn_sureadd);
        btn_sureadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTime = edTime.getText().toString();
                String getHint = edHint.getText().toString();

                if (getTime.isEmpty()){
                    edTime.setError("please input date and time...");
                }else if (getHint.isEmpty()){
                    edHint.setError("please input hospital and number...");
                }else{
                    database.child("Users").push().setValue(new ModelUsers(getTime, getHint)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AdddActivity.this, "oooh yeah! data success! ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AdddActivity.this, MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdddActivity.this, "ooooh no! data fail! ", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}