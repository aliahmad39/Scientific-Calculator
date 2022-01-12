package com.ali.trainee_taskcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.ali.trainee_taskcalculator.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    static CharSequence  tv1str=null,tv2str=null;
    Calculator cal=new Calculator();
    Boolean flag=true,eqflag=true;
    DecimalFormat format = new DecimalFormat();
    boolean checkDot = false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        format.setDecimalSeparatorAlwaysShown(false);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
    //    getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white));// set status background white


        binding.tv1.setText(tv1str);
        binding.tv2.setText(tv2str);


    }


    private void entryNos(String nos)
    {
        binding.tv1.append(nos);
    }


    private int checkOpr(char c)
    {
        switch (c) {
            case '+':
            case '-':
            case '/':
            case 'x':
                return 1;
            case '(':
                return 2;
            case ')':
                return 3;
            case '.':
                return 4;
            case 'e':
            case '\u03c0':
                return 5;
            case '\u221a':
                return 7;
            default:
                return 0;
        }
    }


    private void checkAMD(String s)
    {
        String str = binding.tv1.getText().toString();
        int len = str.length();
        if(len != 0 )
        {
            char c=str.charAt(len-1);
            int i=checkOpr(c);
            if(i!=2){
                if(i==1)
                {
                    binding.tv1.setText(str.substring(0,len-1));
                    if(len>1)
                    {
                        int j = checkOpr(str.charAt(len-2));
                        if(j==1 || j==2 )
                            binding.tv1.setText(str.substring(0,len-2));
                    }
                    entryNos(s);
                    return;
                }
                entryNos(s);
            }
        }
    }


    private void checkMinus()
    {
        String str = binding.tv1.getText().toString();
        int len = str.length();

        if(len!=0)
        {
            char c = str.charAt(len-1);

            if(c=='+'||c=='-')
            {
                binding.tv1.setText(str.substring(0,len-1));
                entryNos("-");
                return;
            }
        }
        entryNos("-");
    }


    private void checkNos(String s)
    {
        String str = binding.tv1.getText().toString();
        int len = str.length();

        if(len!=0 && checkOpr(s.charAt(0))==5)
        {
            char c = str.charAt(len-1);
            int i = checkOpr(c);
            if(i==3||i==5||i==0||i==6)
                entryNos("x"+s);
            else
                entryNos(s);
            return;
        }

        if(len!=0 && (checkOpr(str.charAt(len-1))==3 || checkOpr(str.charAt(len-1))==5 || checkOpr(str.charAt(len-1))==6))
            entryNos("x"+s);
        else
            entryNos(s);
    }


    private void checkFunc(String s)
    {
        String str = binding.tv1.getText().toString();
        int len = str.length();

        if(len!=0)
        {
            char c = str.charAt(len-1);
            int i = checkOpr(c);
            if(i==0||i==3||i==5||i==6){
                entryNos("x"+s);
                return;
            }
        }
        entryNos(s);
    }


    private void checkBrk() {

        String str = binding.tv1.getText().toString();
        int len = str.length();

        if(len!=0)
        {
            int i=checkOpr(str.charAt(len-1));
            if( i==3 || i==0|| i==4)
            {
                entryNos("x(");
                return;
            }
            entryNos("(");
        }
        else
            entryNos("(");
    }


    private void checkDot()
    {
        if( checkDot == false){
            String str = binding.tv1.getText().toString();
            int len = str.length();
            checkDot = true;

            if(len!=0)
            {
                int i = checkOpr(str.charAt(len-1));
                if(i==4)
                    return;
                if(i==3)
                {
                    entryNos("x.");
                    return;
                }
            }
            entryNos(".");
        }

    }


    private void finalResult(String result)
    {
        if(result!=null){
            try {
                binding.tv2.setText(format.format(Double.parseDouble(result)));
            }catch (Exception e){
             //   Toast.makeText(this, "error :"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }


    private void tempResult(){

        cal.expression(binding.tv1.getText()+"=");

        String str = cal.result();

        if(str.equals("ERROR"))
            finalResult(null);
        else
            finalResult(str);

    }

    public void clickHandle(View v) {

        if(flag){

            switch (v.getId()) {
                case R.id.btn_1:
                    checkNos("1");
                    break;

                case R.id.btn_2:
                    checkNos("2");
                    break;

                case R.id.btn_3:
                    checkNos("3");
                    break;

                case R.id.btn_4:
                    checkNos("4");
                    break;

                case R.id.btn_5:
                    checkNos("5");
                    break;

                case R.id.btn_6:
                    checkNos("6");
                    break;

                case R.id.btn_7:
                    checkNos("7");
                    break;

                case R.id.btn_8:
                    checkNos("8");
                    break;

                case R.id.btn_9:
                    checkNos("9");
                    break;

                case R.id.btn_0:
                    checkNos("0");
                    break;

                case R.id.btn_pi:
                    checkNos("\u03c0");
                    break;

                case R.id.btn_e:
                    checkNos("e");
                    break;

                case R.id.btn_sin:
                    checkFunc("sin(");
                    break;

                case R.id.btn_plus:
                    checkAMD("+");
                    checkDot = false;
                    break;

                case R.id.btn_minus:
                    checkMinus();
                    checkDot = false;
                    break;

                case R.id.btn_mult:
                    checkAMD("x");
                    checkDot = false;
                    break;

                case R.id.btn_div:
                    checkAMD("/");
                    checkDot = false;
                    break;

                case R.id.btn_dot:
                    checkDot();
                    break;

                case R.id.btn_bracket_close:
                    entryNos(")");
                    break;

                case R.id.btn_bracket_open:
                    checkBrk();
                    break;

                case R.id.btn_clear:
                    binding.tv1.setText(null);
                    binding.tv2.setText(null);
                    tv1str = null;
                    tv2str = null;
                    checkDot = false;
                    break;


                case R.id.btn_equal:
                    binding.tv2.setText(null);
                    eqflag=false;
                    cal.expression(binding.tv1.getText()+"=");
                    String str = cal.result();
                    if(str!=null){
                        if(str.equals("ERROR")){
                            binding.tv1.setTextColor(Color.RED);
                            flag=false;
                        }
                        binding.tv1.setText(str);
                    }
                    break;
                default:
                    break;
            }

            if(eqflag)
                tempResult();
            eqflag=true;

        }
        else if(v.getId()==R.id.btn_clear)
        {
            flag=true;
            binding.tv1.setText(null);
            binding.tv2.setText(null);
            tv1str = null;
            tv2str = null;
        }
        tv1str = binding.tv1.getText();
        tv2str = binding.tv2.getText();
    }

    public void secondActivity(View view) {
        startActivity(new Intent(MainActivity.this, loginActivity.class));
    }
}




