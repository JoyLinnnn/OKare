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

public class AdddActivity2 extends AppCompatActivity {
    EditText edTime2, edHint2;
    Button btn_sureadd2;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addd2);

        edTime2 = findViewById(R.id.edTime2);
        edHint2 = findViewById(R.id.edHint2);
        btn_sureadd2 = findViewById(R.id.btn_sureadd2);
        btn_sureadd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTime2 = edTime2.getText().toString();
                String getHint2 = edHint2.getText().toString();

                if (getTime2.isEmpty()){
                    edTime2.setError("please input date and time...");
                }else if (getHint2.isEmpty()){
                    edHint2.setError("please input medicine and number...");
                }else{
                    database.child("Users").push().setValue(new ModelUsers(getTime2, getHint2)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AdddActivity2.this, "資料新增成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AdddActivity2.this, MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdddActivity2.this, "資料新增失敗", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}