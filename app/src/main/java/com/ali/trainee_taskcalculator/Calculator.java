package com.ali.trainee_taskcalculator;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Stack;

public class Calculator  {
    private Stack<String> st = new Stack<String>() ;
    private Stack<String> data = new Stack<String>();
    private DecimalFormat df = new DecimalFormat("#.000000");
    private Exception excp = new Exception();

    String exp;

    public Calculator() {
        this.exp=null;
    }


    public void expression(String exp)
    {
        this.exp = exp;
    }


    private double conv(String str)
    {
        if(str.equals("."))
            str="0";
        return Double.parseDouble(str);
    }



    // NEGATIVE NUMBER CHECK
    private boolean negCheck(char c)
    {
        switch (c) {
            case '+':
            case 'x':
            case '/':
            case '(':
                return true;
            default:
                return false;
        }
    }



// 	PRIORITY MANAGER

    private int priority(String s)
    {
        if(s.equals("-") || s.equals("+"))
            return 1;
        if(s.equals("x") || s.equals("/"))
            return 2;
        if(s.equals("sin"))
            return 5;
        if(s.equals("\u221a"))
            return 5;
        return 0;
    }


//EVALUATION LOGIC

    private void evaluate(String s) throws Exception
    {
        if(s.equals(")"))
        {
            if(st.empty() && data.empty())
                throw excp;
            if(!data.empty() && st.empty())
                return;
            String str = st.pop();
            while(!str.equals("("))
            {
                evaluate(str);
                if(st.empty())
                    break;
                str=st.pop();
            }
            return;
        }
        if(s.equals("/"))
        {
            double temp1=conv(data.pop()),temp2=conv(data.pop());
            data.push(temp2/temp1+"");
            return;
        }
        if(s.equals("x"))
        {
            double temp1=conv(data.pop()),temp2=conv(data.pop());
            data.push(temp2*temp1+"");
            return;
        }
        if(s.equals("+"))
        {
            double temp1=conv(data.pop()),temp2=conv(data.pop());

            data.push((temp2+temp1)+"");
            return;
        }
        if(s.equals("-"))
        {
            double temp1=conv(data.pop()),temp2=conv(data.pop());
            data.push((temp2-temp1)+"");
            return;
        }

        if(s.equals("sin"))
        {
            double temp1 = conv(data.pop());
            data.push(conv(df.format(Math.sin(temp1)+1))-1+"");
            return;
        }

        if(s.equals("\u221a"))
        {
            double temp1 = conv(data.pop());
            data.push(Math.sqrt(temp1)+"");
            return;
        }
    }

//OPERATOR STACK MANAGER AND BODMAS MANAGER

    private void oprStack(String c,int p) throws Exception
    {

        if(st.empty()){
            st.push(c);
            return;
        }

        if(c.equals(")"))
        {
            evaluate(c);
            return;
        }

        while(!st.empty())
        {
            String str = st.pop();
            int prio = priority(str);

            if(str.equals("(") || p>prio)
            {
                st.push(str);
                if(c.equals("!"))
                {
                    evaluate(c);return;
                }
                break;
            }
            if(p<=prio)
            {
                evaluate(str);
                if(p==4)
                {
                    evaluate(c);return;
                }
            }
        }
        st.push(c);

    }



//EXPRESSION FETCHING

    private void fetch() throws Exception
    {

        String temp="";

        int length = exp.length();

        int i=0;

        while(i<length)
        {
            char c = exp.charAt(i);

            switch(c){
                case '-':{

                    if(i==0 || negCheck(exp.charAt(i-1)))
                        temp="-";
                    else {
                        if(!temp.equals(""))
                            data.push(temp);
                        oprStack("-",1);
                        temp="";
                    }
                    break;
                }
                case '+':{
                    if(!temp.equals(""))
                        data.push(temp);
                    oprStack("+",1);
                    temp="";
                    break;
                }
                case 'x':{
                    if(!temp.equals(""))
                        data.push(temp);
                    oprStack("x",2);
                    temp="";
                    break;
                }
                case '/':{
                    if(!temp.equals(""))
                        data.push(temp);
                    oprStack("/",2);
                    temp="";
                    break;
                }
                case '(':{
                    st.push("(");
                    temp="";
                    break;
                }
                case ')':{
                    if(!temp.equals(""))
                        data.push(temp);
                    oprStack(")",0);
                    temp="";
                    break;
                }
                case '\u221a':
                    if(!temp.equals(""))
                        data.push(temp);
                    oprStack("\u221a",5);
                    temp="";
                    break;
                case '=':
                {
                    if(!temp.equals(""))
                        data.push(temp);
                    break;
                }
                default:
                {
                    temp=temp+c;
                    if(temp.equals("sin"))
                    {
                        st.push(temp);
                        temp="";
                        break;
                    }
                    if(temp.equals('\u03c0'+""))
                    {
                        data.push("3.141592656");
                        temp="";
                        break;
                    }
                    if(temp.equals("e"))
                    {
                        data.push(Math.E+"");
                        temp="";
                        break;
                    }

                }
            }
            i++;
        }
    }



// FUNC TO START MANIPULATION AND PROVIDE FINAL RESULT

    public String result()
    {
        data.removeAllElements();
        st.removeAllElements();
        try{
            if(exp!=null){
                fetch();
                if(!data.empty()){
                    while(!st.empty()){
                        evaluate(st.pop());
                    }
                    String str = data.pop();
                    if(str.equals("."))
                        return "0.0";
                    return str;
                }
            }
        }
        catch(Exception e){
            return "ERROR";
        }
        return "";
    }

}
