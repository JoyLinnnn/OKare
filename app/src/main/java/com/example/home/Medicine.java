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
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);


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

        ImageButton 服藥_IB=findViewById(R.id.服藥_IB);
        服藥_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine.this,Medicine.class);
                startActivity(intent);
            }
        });
        ImageButton 回診_IB=findViewById(R.id.回診_IB);
        回診_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine.this,Doctor.class);
                startActivity(intent);
            }
        });

        ImageButton 醫療小卡_IB = findViewById(R.id.醫療小卡_IB);
        醫療小卡_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Medicine.this, MedicalCard.class);
                startActivity(intent);

            }
        });
        ImageButton 提醒事項_IB = findViewById(R.id.提醒事項_IB);
        提醒事項_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Medicine.this, Medicine.class);
                startActivity(intent);

            }
        });


        ImageButton 居_IB=findViewById(R.id.居_IB);
        居_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton 醫_IB=findViewById(R.id.醫_IB);
        醫_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine.this,MedicalCard.class);
                startActivity(intent);
            }
        });
        ImageButton 預_IB=findViewById(R.id.預_IB);
        預_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine.this,ReserveHospital.class);
                startActivity(intent);
            }
        });
        ImageButton 即_IB=findViewById(R.id.即_IB);
        即_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine.this,CurrentLocation.class);
                startActivity(intent);
            }
        });
        ImageButton 衛_IB=findViewById(R.id.衛_IB);
        衛_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine.this,edu_main.class);
                startActivity(intent);
            }
        });
        ImageButton 飲_IB=findViewById(R.id.飲_IB);
        飲_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine.this,commendMain.class);
                startActivity(intent);
            }
        });

/*        ImageButton 通_通知_IB=findViewById(R.id.通_通知_IB);
        通_通知_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine.this,noti.class);
                startActivity(intent);
            }
        });

 */
        ImageButton 通_個人_IB=findViewById(R.id.通_個人_IB);
        通_個人_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine.this,Personal.class);
                startActivity(intent);
            }
        });
        ImageButton 通_首頁_IB=findViewById(R.id.通_首頁_IB);
        通_首頁_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine.this,Homepage.class);
                startActivity(intent);
            }
        });


    }
}