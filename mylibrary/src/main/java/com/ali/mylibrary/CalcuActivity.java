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
        getSupportActionBar().hide();
        bindViews();
        clickListeners();


    }

    private void clickListeners() {
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num1.getText().toString().isEmpty()){
                    num1.setError("Enter num 1!");
                }
                if(num2.getText().toString().isEmpty()){
                    num2.setError("Enter num 2!");
                    return;
                }
                double a = Double.parseDouble(num1.getText().toString());
                double b = Double.parseDouble(num2.getText().toString());
                double c = a + b;
                int res = (int) Math.round(c);
                ToasterMessage.display(CalcuActivity.this, res);
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