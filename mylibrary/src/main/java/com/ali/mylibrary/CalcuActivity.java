package com.ali.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalcuActivity extends AppCompatActivity {
    EditText num1, num2;
    Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcu);
        bindViews();
        clickListeners();


    }

    private void clickListeners() {
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(num1.getText().toString());
                double b = Double.parseDouble(num2.getText().toString());
                double c = a + b;
                ToasterMessage.display(CalcuActivity.this, Integer.parseInt(String.valueOf(c)));
                finish();
            }
        });
    }

    private void bindViews() {
        num1 = findViewById(R.id.et_1);
        num2 = findViewById(R.id.et_2);
        check = findViewById(R.id.btn_submit);
    }
}