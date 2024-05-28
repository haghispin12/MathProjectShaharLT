package com.example.mathprojectshaharlt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private Button submitUser;
    private EditText UserNameLabel;
    private EditText PassWordLabel;
    private EditText ConfirmLabel;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViewSignUp();
        auth = FirebaseAuth.getInstance();
        submitUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.createUserWithEmailAndPassword(UserNameLabel.getText().toString(),PassWordLabel.getText().toString()).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this,"regisration success",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, MainZikaron.class);

                        }else{
                            Toast.makeText(SignUp.this,"regisration failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

    }
    private void initViewSignUp(){
        submitUser = findViewById(R.id.submitUser);
        UserNameLabel = findViewById(R.id.userNameLabel);
        PassWordLabel = findViewById(R.id.passwordLabel);
        ConfirmLabel = findViewById(R.id.confirmLabel);
    }
}