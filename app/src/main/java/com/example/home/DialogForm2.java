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

public class DialogForm2 extends DialogFragment {
    String MT, MN, cey, choose;
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    public DialogForm2(String MT, String MN, String cey, String choose){
        this.MT = MT;
        this.MN = MN;
        this.cey = cey;
        this.choose = choose;
    }
    TextView tMT,tMN;
    Button btn_suadd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.activity_ad, container, false);
        tMT=view.findViewById(R.id.edMT);
        tMN=view.findViewById(R.id.edMN);
        btn_suadd=view.findViewById(R.id.確定新增);

        tMT.setText(MT);
        tMN.setText(MN);
        btn_suadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MT = tMT.getText().toString();
                String aMN = tMN.getText().toString();
                if (choose.equals("Change")){
                    data.child("Medicine").child(cey).setValue(new ModelMedicine(MT, aMN)).addOnSuccessListener(new OnSuccessListener<Void>() {
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
