package com.example.mathprojectshaharlt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mathprojectshaharlt.mathproject.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private Button submitZikaron;
    private Button signUpZikaron;
    private EditText userNameZikaron;
    private EditText passWordZikaron;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViewLogin();
    }
    private void initViewLogin(){
        submitZikaron = findViewById(R.id.submitZikaron);
        signUpZikaron = findViewById(R.id.signUp);
        userNameZikaron = findViewById(R.id.userNameZikaron);
        passWordZikaron = findViewById(R.id.PasswordZikaron);

    signUpZikaron.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, SignUp.class);
        }
    });
    }
}