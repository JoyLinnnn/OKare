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
    FloatingActionButton ad;
    AdapterMedicine adapterMedicine;
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelMedicine> listMedicine;
    RecyclerView tv_tamp;
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
        ad = findViewById(R.id.btn_ad);
        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Medicine.this, AdActivity.class));
            }
        });
        tv_tamp = findViewById(R.id.tv_tamp);
        RecyclerView.LayoutManager mmLayout = new LinearLayoutManager(this);
        tv_tamp.setLayoutManager(mmLayout);
        tv_tamp.setItemAnimator(new DefaultItemAnimator());

        tampData();
    }

    private void tampData() {
        data.child("Medicine").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listMedicine = new ArrayList<>();
                for(DataSnapshot itemm:snapshot.getChildren()){
                    ModelMedicine mdc = itemm.getValue(ModelMedicine.class);
                    mdc.setCey(itemm.getKey());
                    listMedicine.add(mdc);
                }
                adapterMedicine = new AdapterMedicine(listMedicine, Medicine.this);
                tv_tamp.setAdapter(adapterMedicine);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}