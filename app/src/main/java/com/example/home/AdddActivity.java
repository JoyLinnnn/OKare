package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Calendar;

public class AdddActivity extends AppCompatActivity {
    EditText edHint;
    TextView edNew,edTime;
    Button btn_sureadd;

    private TextView tvOnceTime, tvOnceDate;
    private ImageButton ibOnceTime, ibOnceDate;
    private EditText etOnceMessage;
    private Button btnSetOnceAlarm;

    private AlarmReceiver alarmReceiver;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int mHourRepeat, mMinuteRepeat;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addd);

        edNew= findViewById(R.id.edNew);
        edTime = findViewById(R.id.edTime);
        edHint = findViewById(R.id.edHint);
        btn_sureadd = findViewById(R.id.確定新增);
        btn_sureadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNew1 = edNew.getText().toString();
                String getTime = edTime.getText().toString();
                String getHint = edHint.getText().toString();

                if (getNew1.isEmpty()) {
                    edNew.setError("請選擇回診日期...");
                }if (getTime.isEmpty()) {
                    edTime.setError("請選擇回診時間...");
                } else if (getHint.isEmpty()) {
                    edHint.setError("請輸入醫院門診及編號...");
                } else {
                    database.child("Users").push().setValue(new ModelUsers(getNew1, getTime, getHint)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AdddActivity.this, "回診提醒輸入成功 ! ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AdddActivity.this, Doctor.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdddActivity.this, "回診提醒輸入失敗 ! ", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        tvOnceTime = findViewById(R.id.edTime);
        tvOnceDate = findViewById(R.id.edNew);
        ibOnceTime = findViewById(R.id.ib_once_time);
        ibOnceDate = findViewById(R.id.ib_once_date);
        etOnceMessage = findViewById(R.id.edHint);
        btnSetOnceAlarm = findViewById(R.id.btn_set_once_alarm);

        alarmReceiver = new AlarmReceiver();

        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        mHourRepeat = mHour;
        mMinuteRepeat = mMinute;

        ibOnceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AdddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvOnceDate.setText(String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth));
                        mYear = year;
                        mMonth = month;
                        mDay = dayOfMonth;
                    }
                },mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        ibOnceTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AdddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvOnceTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                        mHour = hourOfDay;
                        mMinute = minute;
                    }
                }, mHour, mMinute,true);
                timePickerDialog.show();
            }
        });


        btnSetOnceAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvOnceDate.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(AdddActivity.this, "日期是空的 !", Toast.LENGTH_SHORT).show();
                } else if (tvOnceTime.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(AdddActivity.this, "時間是空的 !", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etOnceMessage.getText().toString())) {
                    etOnceMessage.setError("訊息不能是空的 !");
                } else {
                    alarmReceiver.setOneTimeAlarm(AdddActivity.this, AlarmReceiver.TYPE_ONE_TIME,
                            tvOnceDate.getText().toString(), tvOnceTime.getText().toString(),
                            etOnceMessage.getText().toString());
                }
            }
        });
    }
}