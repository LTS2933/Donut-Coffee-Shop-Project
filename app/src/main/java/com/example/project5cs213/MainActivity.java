package com.example.project5cs213;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button orderDonuts;
    private Button orderCoffee;
    private Button orderBasket;
    private Button storeOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.orderDonuts = (Button) findViewById(R.id.button);
        this.orderDonuts.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), DonutActivity.class);
                startActivity(intent);
            }
        });
        this.orderCoffee = (Button) findViewById(R.id.button2);
        this.orderCoffee.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), CoffeeActivity.class);
                startActivity(intent);
            }
        });
        this.orderBasket = (Button) findViewById(R.id.button3);
        this.orderBasket.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), DonutActivity.class);
                startActivity(intent);
            }
        });
        this.storeOrders = (Button) findViewById(R.id.button4);
        this.storeOrders.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), OrdersActivity.class);
                startActivity(intent);
            }
        });
    }
}
