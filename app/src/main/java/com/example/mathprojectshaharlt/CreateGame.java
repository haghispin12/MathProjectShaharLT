package com.example.mathprojectshaharlt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.gson.Gson;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.DocumentSet;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

public class CreateGame extends AppCompatActivity {
    private EditText GameCode;
    private Button CreateGame;
    private EditText JoinId;
    private Button Join;
    private Button Practice;
    private MainVM mainVM;
    FirebaseAuth auth;
    String gameDocId = "";
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
        mainVM = new ViewModelProvider(this).get(MainVM.class);
        CollectionReference collectionRef = FirebaseFirestore.getInstance().collection("games");
        Gson gson = new Gson();
        Join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JoinId.setVisibility(View.VISIBLE);
                String code = JoinId.getText().toString().trim();
                if (!code.isEmpty()) {
                    collectionRef.whereEqualTo("gameCode", JoinId.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            String gameid = "";
                            for (DocumentSnapshot dc : queryDocumentSnapshots) {
                                gameid = dc.getId();
                                Map<String, Object> updates = new HashMap<>();
                                updates.put("player2", auth.getCurrentUser().getEmail());
                                updates.put("status", 1);
                                collectionRef.document(gameid).update(updates);
                                //collectionRef.document(gameid).update("player2", auth.getCurrentUser().getEmail());
                                //collectionRef.document(gameid).update("status",1);
                            }
                            Intent intent = new Intent(CreateGame.this, MainZikaron.class);
//                            String code = JoinId.getText().toString();
                            intent.putExtra("code", code);
                            intent.putExtra("gameId", gameid);
//                            intent.getStringExtra(code);
//                            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString("code", code);
//                            editor.apply();
//                            mainVM.setGameCode(code);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
        Practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateGame.this, MainZikaron.class);
                startActivity(intent);
            }
        });

        CreateGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
                GameCode.setVisibility(View.VISIBLE);
                ArrayList<Card>cards = mainVM.Cards.getValue();
                String json = gson.toJson(cards);
                Game game = new Game(code,auth.getCurrentUser().getEmail(),"0",0, cards);
                //DocumentReference docRef = FirebaseFirestore.getInstance().collection("games").document(gameDocId);

                FirebaseFirestore.getInstance().collection("games").add(game).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        gameDocId = documentReference.getId();
                        GameCode.setText(code);
                        Toast.makeText(CreateGame.this,"game created",Toast.LENGTH_SHORT).show();
                        DocumentReference docRef = FirebaseFirestore.getInstance().collection("games").document(gameDocId);
                        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                                    if(documentSnapshot.exists()){
                                        long statusValue = documentSnapshot.getLong("status");
                                        if(1 == statusValue){
                                            Intent intent = new Intent(CreateGame.this,MainZikaron.class);
                                            intent.putExtra("code", code);
                                            intent.putExtra("gameId",gameDocId);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            });
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