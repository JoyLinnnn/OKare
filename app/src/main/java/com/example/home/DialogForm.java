package com.example.home;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogForm extends DialogFragment {
    String New,Time, Hint, key, select;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogForm(String New,String Time,String Hint,String key,String select){
        this.New=New;
        this.Time=Time;
        this.Hint=Hint;
        this.key=key;
        this.select=select;
    }
    TextView tNew,tTime,tHint;
    Button btn_sureadd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.activity_addd,container,false);
        tNew=view.findViewById(R.id.edNew);
        tTime=view.findViewById(R.id.edTime);
        tHint=view.findViewById(R.id.edHint);
        btn_sureadd=view.findViewById(R.id.確定新增);

        tNew.setText(New);
        tTime.setText(Time);
        tHint.setText(Hint);
        btn_sureadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String New = tNew.getText().toString();
                String Time = tTime.getText().toString();
                String aHint = tHint.getText().toString();
                if (select.equals("change改變")){
                    database.child("Users").child(key).setValue(new ModelUsers(New,Time,aHint)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(view.getContext(), "回診資料更新成功 ! ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), MainActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "回診資料更新失敗 ! ", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}

