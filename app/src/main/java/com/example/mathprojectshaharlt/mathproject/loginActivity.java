package com.example.mathprojectshaharlt.mathproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mathprojectshaharlt.R;

public class loginActivity extends AppCompatActivity {
    private Button submit;
    private EditText userName;



@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViewLogin();
        SharedPreferences sh = getSharedPreferences("mySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("userName","");
        userName.setText(s1);

    }
    private void initViewLogin(){;
    submit = findViewById(R.id.submit);
    userName = findViewById(R.id.userName);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userKey",userName.getText().toString());


    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences sharedPreferences = getSharedPreferences("mySharedPref",MODE_PRIVATE);
            SharedPreferences.Editor MyEdit = sharedPreferences.edit();
            MyEdit.putString("userName",userName.getText().toString());
            MyEdit.apply();
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
            intent.putExtra("userName",userName.getText().toString());
        startActivity(intent);
        }
    });


    userName.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });




    }

}