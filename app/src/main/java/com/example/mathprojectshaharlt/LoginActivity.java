package com.example.mathprojectshaharlt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathprojectshaharlt.mathproject.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button submitZikaron;
    private Button signUpZikaron;
    private EditText userNameZikaron;
    private EditText passWordZikaron;
    FirebaseAuth auth;

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
        auth = FirebaseAuth.getInstance();

    signUpZikaron.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, SignUp.class);
            startActivity(intent);
        }
    });
    submitZikaron.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            auth.signInWithEmailAndPassword(userNameZikaron.getText().toString(),passWordZikaron.getText().toString()).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this,CreateGame.class);
                    startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    });



    }
}