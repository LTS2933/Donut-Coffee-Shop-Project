package com.example.project5cs213;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CoffeeActivity extends AppCompatActivity{

    private Button backToMain;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        this.backToMain = (Button) findViewById(R.id.mainCoffee);
        this.backToMain.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }

}
