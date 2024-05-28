package com.example.mathprojectshaharlt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.DocumentSet;

import java.util.UUID;

public class CreateGame extends AppCompatActivity {
    private EditText GameCode;
    private Button CreateGame;
    private EditText JoinId;
    private Button Join;
    private Button Practice;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        GameCode = findViewById(R.id.gameCode);
        CreateGame = findViewById(R.id.Create);
        JoinId = findViewById(R.id.JoinId);
        Join = findViewById(R.id.Join);
        Practice = findViewById(R.id.parctice);
        auth = FirebaseAuth.getInstance();
        Join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JoinId.setVisibility(View.VISIBLE);
                CollectionReference collectionRef = FirebaseFirestore.getInstance().collection("games");
                collectionRef.whereEqualTo("gameCode",JoinId.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot dc : queryDocumentSnapshots)
                        {
                        String gameid = dc.getId();
                        collectionRef.document(gameid).update("player2", auth.getCurrentUser().getEmail());
                        collectionRef.document(gameid).update("status",1);

                        }
                    }
                });
            }
        });
        Practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateGame.this,MainZikaron.class);
                startActivity(intent);
            }
        });
        CreateGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
                GameCode.setVisibility(View.VISIBLE);
                Game game = new Game(code,auth.getCurrentUser().getEmail(),"0",0);
                FirebaseFirestore.getInstance().collection("games").document().set(game).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CreateGame.this,"game created",Toast.LENGTH_SHORT).show();
                        GameCode.setText(code);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateGame.this,"fail creating game",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}