package com.example.mathprojectshaharlt;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
    private User user;
    private Button ShowUsers;
    private Button challenge;
    private Button until20;
    private Button multyTable;
    private Button clear;
    private Button check;
    private TextView Xtable1;
    private TextView Xtable2;
    private EditText answer;
    private MainViewMoudle mainViewMoudle;
    private Button Rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        showToast("hello"+" "+ userName);
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
        Rate = findViewById(R.id.Rate);
        ShowUsers = findViewById(R.id.showUsers);
        Exercise E = new Exercise();
        mainViewMoudle = new ViewModelProvider(this).get(MainViewMoudle.class);
        user = new User();

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int myRate = result.getData().getIntExtra("rate",-1);
                        showToast(myRate+"");
                    }
                });






        Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Rate.class);
                activityResultLauncher.launch(intent);
            }
        });

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
                int ans = Integer.parseInt(answer.getText().toString());
                if (E.check(ans)) {
                    showToast("good job");
                    user.setScore(mainViewMoudle.getLastScore());
                }
                else
                    showToast("you fail - try again later");
                answer.setText("");

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Xtable1.setText("");
                Xtable2.setText("");
                answer.setText("");
            }
        });
        ShowUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                trans.add(R.id.frameLayout, new ShowUsers());
                trans.commit();
            }
        });

        mainViewMoudle.LDnum1.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Xtable1.setText(integer+"");
            }
        });
        mainViewMoudle.LDnum2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
              Xtable2.setText(integer+"");
            }
        });

    }

}