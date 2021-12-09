package com.ali.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CalcuActivity extends AppCompatActivity {
    EditText num1 , num2;
    Button check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcu);
        num1 = findViewById(R.id.et_1);
        num2 = findViewById(R.id.et_2);
        check = findViewById(R.id.btn_submit);




    }
}