package com.example.mathprojectshaharlt.mathproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.mathprojectshaharlt.R;

public class Rate extends AppCompatActivity {
private SeekBar sb;
private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        initRate();
    }
    public void initRate(){
        sb = findViewById(R.id.sb);
        save = findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("rate",sb.getProgress());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}