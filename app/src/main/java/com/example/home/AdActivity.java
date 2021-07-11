package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

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

    DatabaseReference data = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        edMT = findViewById(R.id.tvMT);
        edMN = findViewById(R.id.edMN);
        btn_suadd = findViewById(R.id.確定新增);
        btn_suadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getMT = edMT.getText().toString();
                String getMN = edMN.getText().toString();

                if (getMT.isEmpty()){
                    edMT.setError("請輸入日期...");
                }else if (getMN.isEmpty()){
                    edMN.setError("請輸入藥品數字...");
                }else{
                    data.child("Medicine").push().setValue(new ModelMedicine(getMT, getMN)).addOnSuccessListener(new OnSuccessListener<Void>() {
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