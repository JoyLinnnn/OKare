package com.example.home;

import android.app.Dialog;
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
    String Time, Hint, key, select;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogForm(String Time,String Hint,String key,String select){
        this.Time=Time;
        this.Hint=Hint;
        this.key=key;
        this.select=select;
    }
    TextView tTime,tHint;
    Button btn_sureadd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.activity_addd,container,false);
        tTime=view.findViewById(R.id.edTime);
        tHint=view.findViewById(R.id.edHint);
        btn_sureadd=view.findViewById(R.id.確定新增);

        tTime.setText(Time);
        tHint.setText(Hint);
        btn_sureadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Time = tTime.getText().toString();
                String aHint = tHint.getText().toString();
                if (select.equals("change")){
                    database.child("Users").child(key).setValue(new ModelUsers(Time,aHint)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(view.getContext(), "更新成功! ", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "更新失敗! ", Toast.LENGTH_SHORT).show();
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
