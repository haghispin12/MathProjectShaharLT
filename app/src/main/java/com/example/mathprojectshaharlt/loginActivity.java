package com.example.mathprojectshaharlt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {
    private Button submit;
    private EditText userName;



@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViewLogin();
    }
    private void initViewLogin(){;
    submit = findViewById(R.id.submit);
    userName = findViewById(R.id.userName);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userKey",userName.getText().toString());

    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
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