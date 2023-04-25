package com.example.project5cs213;

import static android.util.Log.ERROR;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.project5cs213.Donut;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void showToast(String exception) {
        Toast.makeText(this.getApplicationContext(),
                "Error: " + exception, Toast.LENGTH_SHORT).show();
    }
    protected void showDonutScreen(View view) {
        try {
            Intent donutIntent = new Intent(this,
                    DonutActivity.class);
            startActivity(donutIntent);
        } catch (IllegalStateException exception) {
            showToast(exception.getMessage());
        }
    }
    protected void showCoffeeScreen(View view) {
        try {
            Intent coffeeIntent = new Intent(this,
                    CoffeeActivity.class);
            startActivity(coffeeIntent);
        } catch (IllegalStateException exception) {
            showToast(exception.getMessage());
        }
    }
    protected void showOrderingBasketScreen(View view) {
        try {
            Intent basketIntent = new Intent(this, BasketActivity.class);
            startActivity(basketIntent);
        } catch (IllegalStateException exception) {
            showToast(exception.getMessage());
        }
    }
    protected void showStoreOrdersScreen(View view) {
        try {
            Intent intent = new Intent(this,
                    OrdersActivity.class);
            startActivity(intent);
        } catch (IllegalStateException exception) {
            showToast(exception.getMessage());
        }
    }


}