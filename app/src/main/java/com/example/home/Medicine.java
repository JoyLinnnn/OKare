package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Medicine extends AppCompatActivity {
    FloatingActionButton addd2;
    AdapterUsers adapterUsers;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelUsers> listUsers;
    RecyclerView tv_tampil2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        ImageButton 回診_IB=findViewById(R.id.回診_IB);
        回診_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine.this,Doctor.class);
                startActivity(intent);
            }
        });
        addd2 = findViewById(R.id.btn_addd2);
        addd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Medicine.this, AdddActivity2.class));
            }
        });
        tv_tampil2 = findViewById(R.id.tv_tampil2);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        tv_tampil2.setLayoutManager(mLayout);
        tv_tampil2.setItemAnimator(new DefaultItemAnimator());

        tempilDate();
    }

    private void tempilDate() {
        database.child("Medicine").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUsers = new ArrayList<>();
                for(DataSnapshot item:snapshot.getChildren()){
                    ModelUsers ur = item.getValue(ModelUsers.class);
                    ur.setKey(item.getKey());
                    listUsers.add(ur);
                }
                adapterUsers = new AdapterUsers(listUsers, Medicine.this);
                tv_tampil2.setAdapter(adapterUsers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
