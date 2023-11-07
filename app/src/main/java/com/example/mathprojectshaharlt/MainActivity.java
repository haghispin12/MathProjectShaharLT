package com.example.mathprojectshaharlt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private void showToast (String s){
    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    private Button challenge;
    private Button until20;
    private Button multyTable;
    private Button clear;
    private Button check;
    private TextView Xtable1;
    private TextView Xtable2;
    private EditText answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView(){
        challenge = findViewById(R.id.challenge);
        until20 = findViewById(R.id.until20);
        multyTable = findViewById(R.id.multyTable);
        clear = findViewById(R.id.clear);
        check = findViewById(R.id.check);
        Xtable1 = findViewById(R.id.Xtable1);
        Xtable2 = findViewById(R.id.Xtable2);
        answer = findViewById(R.id.answer);
        Exercise E = new Exercise();



        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                E.challenge();
                Xtable1.setText(E.getNum()+"");
                Xtable2.setText(E.getNum1()+"");
            }
        });
        until20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                E.until20();
                Xtable1.setText(E.getNum()+"");
                Xtable2.setText(E.getNum1()+"");
            }
        });
        multyTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                E.multyTable();
                Xtable1.setText(E.getNum()+"");
                Xtable2.setText(E.getNum1()+"");
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(Xtable1.getText().toString());
                int num1 = Integer.parseInt(Xtable2.getText().toString());
                int ans = Integer.parseInt(answer.getText().toString());
                if(E.check(num,num1,ans))
                    showToast("good job");
                else
                    showToast("you fail - try again later");
                answer.setText("");

            }
        });
    }


}