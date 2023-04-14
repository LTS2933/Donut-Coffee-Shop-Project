package com.example.project5cs213;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DonutActivity extends AppCompatActivity{

    private Button backToMain;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        this.backToMain = (Button) findViewById(R.id.mainDonut);
        this.backToMain.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }
}
