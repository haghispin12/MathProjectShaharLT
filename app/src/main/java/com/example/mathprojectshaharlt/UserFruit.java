package com.example.mathprojectshaharlt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class UserFruit extends AppCompatActivity {
    private RecyclerView rcShowUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_fruit);
        rcShowUsers = findViewById(R.id.rcShowUsers);
        ArrayList<Fruits>fruits = new ArrayList<>();
        fruits.add(new Fruits("banana",R.drawable.banana));
        fruits.add(new Fruits("apple",R.drawable.apple));
        fruits.add(new Fruits("fruit",R.drawable.fru));
        fruits.add(new Fruits("grapes",R.drawable.grapes));
        fruits.add(new Fruits("lemon",R.drawable.lemon));
        fruits.add(new Fruits("orange",R.drawable.orange));

        MyFruirtsAdapter myFruirtsAdapter = new MyFruirtsAdapter(fruits,new MyFruirtsAdapter.OnItemClickListerner(){
            @Override
            public void onItemClick(Fruits item){
                Toast.makeText(UserFruit.this, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        rcShowUsers.setLayoutManager(new LinearLayoutManager(this));
        rcShowUsers.setAdapter(myFruirtsAdapter);
        rcShowUsers.setHasFixedSize(true);




        }
    }
