package com.example.calculatormain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    private EditText textval1,textval2,resVal;
    private Button calButton;
    private TextView tvText;
    private Spinner opsSpin;
    private Logic logic;
    private String selectedOps;
    private  String[] ops={"Choose Operation","Add","Subtract","Multiply","Divide"};
    private double val1,val2,res;
    private static final int ADDITION = 1;
    private static final int SUBTRACTION = 2;
    private static final int MULTIPLICATION = 3;
    private static final int DIVISION = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textval1=findViewById(R.id.textValue1);
        textval2=findViewById(R.id.textValue2);
        calButton=findViewById(R.id.btnCalculate);
        opsSpin=findViewById(R.id.spinner);
        resVal=findViewById(R.id.textResult);
        logic=new Logic();
        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val1=Double.parseDouble(textval1.getText().toString());
                val2=Double.parseDouble(textval2.getText().toString());
                try {
                    if(selectedOps=="Add"){
                        Add sum = new Add();
                        double res = sum.add(val1, val2);
                        resVal.setText(Double.toString(res));
                    }
                    else if(selectedOps=="Subtract"){
                        Subtract sub=new Subtract();
                        double res=sub.Sub(val1,val2);
                        resVal.setText(Double.toString(res));
                    }
                    else if(selectedOps=="Multiply"){
                        Multiply mul=new Multiply();
                        double res=mul.Mul(val1,val2);
                        resVal.setText(Double.toString(res));
                    }
                    else if(selectedOps=="Divide"){
                        Divide div=new Divide();
                        double res=div.Div(val1,val2);
                        double res1=div.Rem(val1,val2);
                        resVal.setText(Double.toString(res)+" R : "+Double.toString(res1));
                    }
                    else if(selectedOps=="Divide"&& val2=='0'){
                        AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
                        alertDialog.setTitle("Warning");
                        alertDialog.setMessage("Value two must not be 0");
                        AlertDialog alertDialog1=alertDialog.create();
                        alertDialog1.show();
                    }
                    else {
                        resVal.setText("Please select operation");
                    }
                }
                catch (Exception e){
                    if(textval1.length()==0){
                        textval1.setError("Please fill the field first");
                    }
                    else if(textval2.length()==0){
                        textval2.setError("Please fill the field first");
                    }
                }
            }
        });
        textval1.setText("");
        textval2.setText("");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,ops);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        opsSpin.setAdapter(adapter);
        opsSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOps=ops[position];
                resVal.setText("You have selected "+selectedOps+" operation");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                resVal.setText("Please select any one option");
            }
        });
        }
    }

