package pl.edu.uwr.pum.calculatorappjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String num;
    private int eq;
    private float num1;
    private String num2;
    private float num3;
    private float num4;
    private TextView screen;
    private String operation;
    private String action;



    @SuppressLint("SetTextI10n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById(R.id.screen);
        screen.setText("");
        num1=0;
        num3=0;
        num4=0;
        eq=0;
        num="";
        num2="";
        operation ="";
        action="";

        if (savedInstanceState != null)
        {
            operation = savedInstanceState.getString("operation");
            action = savedInstanceState.getString("action");
            num2 = savedInstanceState.getString("num2");
            num1 = savedInstanceState.getFloat("num1");
            num3 = savedInstanceState.getFloat("num3");
            eq = savedInstanceState.getInt("eq");
            num4 = savedInstanceState.getFloat("num4");}

        if (screen != null)
            screen.setText(operation);
    }

    @SuppressLint("SetTextI10n")
    public void numberButton1(View view) {

        num = "1";
        if(operation == "")
            operation =num;
        else
            operation += num;
            if(action != "")
                num2 += num;
        screen.setText(operation);
    }

    @SuppressLint("SetTextI10n")
    public void numberButton2(View view) {

        num = "2";
        if(operation == "")
            operation =num;
        else
            operation += num;
            if(action != "")
                num2 += num;
        screen.setText(operation);
    }

    @SuppressLint("SetTextI10n")
    public void numberButton3(View view) {

        num = "3";
        if(operation == "")
            operation =num;
        else
            operation += num;
            if(action != "")
                num2 += num;
        screen.setText(operation);
    }

    @SuppressLint("SetTextI10n")
    public void numberButton4(View view) {

        num = "4";
        if(operation == "")
            operation =num;
        else
            operation += num;
            if(action != "")
                num2 += num;
        screen.setText(operation);
    }

    @SuppressLint("SetTextI10n")
    public void numberButton5(View view) {

        num = "5";
        if(operation == "")
            operation =num;
        else
            operation += num;
            if(action != "")
                num2 += num;
        screen.setText(operation);
    }

    @SuppressLint("SetTextI10n")
    public void numberButton6(View view) {

        num = "6";
        if(operation == "")
            operation =num;
        else
            operation += num;
            if(action != "")
                num2 += num;
        screen.setText(operation);
    }

    @SuppressLint("SetTextI10n")
    public void numberButton7(View view) {

        num = "7";
        if(operation == "")
            operation =num;
        else
            operation += num;
            if(action != "")
                num2 += num;
        screen.setText(operation);
    }

    @SuppressLint("SetTextI10n")
    public void numberButton8(View view) {

        num = "8";
        if(operation == "")
            operation =num;
        else
            operation += num;
            if(action != "")
                num2 += num;
        screen.setText(operation);
    }

    @SuppressLint("SetTextI10n")
    public void numberButton9(View view) {

        num = "9";
        if(operation == "")
            operation =num;
        else
            operation += num;
            if(action != "")
                num2 += num;
        screen.setText(operation);
    }

    @SuppressLint("SetTextI10n")
    public void numberButton0(View view) {

        num = "0";
        if(operation == "")
            operation =num;
        else
            operation += num;
            if(action != "")
                num2 += num;
        screen.setText(operation);
    }

    @SuppressLint("SetTextI10n")
    public void addButton(View view) {
        if (action ==""){
            if(operation == "")
                operation = "";
            else
                num1 = Float.parseFloat(operation);
                operation += "+";
                action = "+";
                num1 = Float.parseFloat(operation.substring(0,operation.length()-1));
            screen.setText(operation);}
    }

    @SuppressLint("SetTextI10n")
    public void clearButton(View view) {
        num1=0;
        num3=0;
        num4=0;
        eq=0;
        num="";
        num2="";
        operation ="";
        action="";
        screen.setText(operation);
    }


    @SuppressLint("SetTextI10n")
    public void subtractButton(View view) {
        if (action ==""){
            if(operation == "")
                operation = "";
            else
                num1 = Float.parseFloat(operation);
                operation += "-";
                action = "-";
                num1 = Float.parseFloat(operation.substring(0,operation.length()-1));
            screen.setText(operation);}
    }

    @SuppressLint("SetTextI10n")
    public void divideButton(View view) {
        if (action ==""){
            if(operation == "")
                operation = "";
            else
                operation += ":";
                action = ":";
                num1 = Float.parseFloat(operation.substring(0,operation.length()-1));
            screen.setText(operation);}
    }

    @SuppressLint("SetTextI10n")
    public void equalButton(View view) {
        if(eq==0) {
            if (action !=""){
                if(operation == "")
                    operation = "";
                else
                    if(action == ":"){
                        num4 = Float.parseFloat(num2);
                        if(num4==0.0){
                            operation = "ERROR";}
                        else if(num4!=0.0){
                            num3 = num1/num4;
                            operation += "=" + Float.toString(num3);}}
                    else if(action == "*"){
                        num4 = Float.parseFloat(num2);
                        num3 = num1*num4;
                        operation += "=" + Float.toString(num3);}
                    else if(action == "+"){
                        num4 = Float.parseFloat(num2);
                        num3 = num1+num4;
                        operation += "=" + Float.toString(num3);}
                    else if(action == "-"){
                        num4 = Float.parseFloat(num2);
                        num3 = num1-num4;
                        operation += "=" + Float.toString(num3);}
                screen.setText(operation);
                eq = 1;}}
    }

    @SuppressLint("SetTextI10n")
    public void multiplyButton(View view) {
        if (action ==""){
            if(operation == "")
                operation = "";
            else
                num1 = Float.parseFloat(operation);
                operation += "*";
                action = "*";
                num1 = Float.parseFloat(operation.substring(0,operation.length()-1));
            screen.setText(operation);}
    }

    @SuppressLint("SetTextI10n")
    public void commaButton(View view) {

        num = ".";
        if(operation == "")
            operation =num;
        else
            operation += num;
        if(action != "")
            num2 += num;
        screen.setText(operation);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("operation", operation);
        outState.putString("action", action);
        outState.putString("num2", num2);
        outState.putFloat("num1", num1);
        outState.putFloat("num3", num3);
        outState.putFloat("num4", num4);
        outState.putInt("eq", eq);
    }
}