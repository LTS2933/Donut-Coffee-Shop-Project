package com.example.project5cs213;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BasketActivity extends AppCompatActivity{

        private Button backToMain;

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ordering_basket_view);
            this.backToMain = (Button) findViewById(R.id.mainBasket);
            this.backToMain.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    finish();
                }
            });
        }

    }
