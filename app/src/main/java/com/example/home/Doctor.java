package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Doctor extends AppCompatActivity {
    FloatingActionButton addd;
    AdapterUsers adapterUsers;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelUsers> listUsers;
    RecyclerView tv_tampil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        addd = findViewById(R.id.btn_addd);
        addd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Doctor.this, AdddActivity.class));
            }
        });
        tv_tampil = findViewById(R.id.tv_tampil);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        tv_tampil.setLayoutManager(mLayout);
        tv_tampil.setItemAnimator(new DefaultItemAnimator());

        tempilDate();
    }

    private void tempilDate() {
        database.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUsers = new ArrayList<>();
                for(DataSnapshot item:snapshot.getChildren()){
                    ModelUsers usr = item.getValue(ModelUsers.class);
                    usr.setKey(item.getKey());
                    listUsers.add(usr);
                }
                adapterUsers = new AdapterUsers(listUsers, Doctor.this);
                tv_tampil.setAdapter(adapterUsers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}