package com.example.home;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class AdActivity extends AppCompatActivity {
    EditText edMN;
    TextView edMT;
    Button btn_suadd;

    private TextView tvRepeatingTime;
    private ImageButton ibRepeatingTime;
    private EditText etRepeatingMessage;
    private Button btnSetRepeatingAlarm, btnCancelRepeatingAlarm;

    private AlarmReceiver alarmReceiver;
    private int mHour, mMinute;
    private int mHourRepeat, mMinuteRepeat;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;

    //DatabaseReference data = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        edMT = findViewById(R.id.tvMT);
        edMN = findViewById(R.id.edMN);
        mAuth=FirebaseAuth.getInstance();
        btn_suadd = findViewById(R.id.確定新增);
        btn_suadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser rUser=mAuth.getCurrentUser();
                assert rUser !=null;
                String userId=rUser.getUid();
                reference = FirebaseDatabase.getInstance().getReference("Medicine").child(userId);
                String getMT = edMT.getText().toString();
                String getMN = edMN.getText().toString();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("userId", userId);
                hashMap.put("mediname", getMN);
                hashMap.put("meditime",getMT);

                if (getMT.isEmpty()){
                    edMT.setError("請輸入日期...");
                }else if (getMN.isEmpty()){
                    edMN.setError("請輸入藥品數字...");
                }else{
                    reference.push().setValue(new ModelMedicine(getMT, getMN)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AdActivity.this, "服藥資料新增成功 ! ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AdActivity.this, Medicine.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdActivity.this, "服藥資料新增失敗 ! ", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        tvRepeatingTime = findViewById(R.id.tvMT);
        ibRepeatingTime = findViewById(R.id.ib_repeating_time);
        etRepeatingMessage = findViewById(R.id.edMN);
        btnSetRepeatingAlarm = findViewById(R.id.btn_set_repeating_alarm);
        btnCancelRepeatingAlarm = findViewById(R.id.btn_cancel_repeating_alarm);

        alarmReceiver = new AlarmReceiver();

        Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        mHourRepeat = mHour;
        mMinuteRepeat = mMinute;




        ibRepeatingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AdActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvRepeatingTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                        mHourRepeat = hourOfDay;
                        mMinuteRepeat = minute;
                    }
                }, mHourRepeat, mMinuteRepeat,true);
                timePickerDialog.show();
            }
        });



        btnSetRepeatingAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvRepeatingTime.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(AdActivity.this, "時間是空的 !", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etRepeatingMessage.getText().toString())){
                    etRepeatingMessage.setError("訊息不能是空的 !");
                } else {
                    alarmReceiver.setRepeatingAlarm(AdActivity.this, AlarmReceiver.TYPE_REPEATING,
                            tvRepeatingTime.getText().toString(),
                            etRepeatingMessage.getText().toString());
                }
            }
        });

        btnCancelRepeatingAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alarmReceiver.isAlarmSet(AdActivity.this, AlarmReceiver.TYPE_REPEATING)) {
                    tvRepeatingTime.setText("");
                    etRepeatingMessage.setText("");
                    alarmReceiver.cancelAlarm(AdActivity.this,AlarmReceiver.TYPE_REPEATING);
                }
            }
        });

    }
}