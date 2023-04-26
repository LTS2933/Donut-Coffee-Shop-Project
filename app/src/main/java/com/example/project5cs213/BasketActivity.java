package com.example.project5cs213;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project5cs213.model.*;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling events relating to the BasketActivity
 * such as creating and registering event listeners to place the order and
 * remove items from the basket
 * @author Christian Osma, Liam Smith
 */
public class BasketActivity extends AppCompatActivity{

    public static final double SALES_TAX = 0.0625;
    private TextInputEditText subTotal;

    private TextInputEditText salesTax;

    private TextInputEditText totalExpense;

    private ListView ordersListView;

    private Order order;

    private ArrayAdapter<MenuItem> items;

    private int indexOfRemoveItem;

    /**
     * This method is called when the activity is created and is responsible for
     * setting content views, adding event listeners, and instantiating variables
     * @param savedInstanceState Bundle object passed into the activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        this.subTotal = (TextInputEditText) this.findViewById(R.id.subtotalTextInput);
        this.salesTax = (TextInputEditText) this.findViewById(R.id.salesTaxEditText);
        this.totalExpense = (TextInputEditText) this.findViewById(R.id.totalEditText);
        this.ordersListView = (ListView) this.findViewById(R.id.ordersListView);
        this.ordersListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3){
                indexOfRemoveItem = position;
            }
        });

        ArrayList<MenuItem> currentOrder = MainController.getCurrentOrder();
        this.items = new ArrayAdapter<MenuItem>(BasketActivity.this, android.R.layout.simple_list_item_1, currentOrder);
        this.order = new Order(currentOrder);
        ordersListView.setAdapter(this.items);
        this.indexOfRemoveItem = -1;
        calculatePrice();
    }

    /**
     * This method is responsible for finishing the current activity and going back to the main activity
     * @param v The view that was clicked
     */
    public void backToMainScreenFromBasket(View v){finish();}

    /**
     * This event listener is responsible for placing an order given the MenuItems
     * in the basket
     * @param v View that was clicked
     */
    public void placeBasketOrder(View v){
        if (MainController.getCurrentOrder().isEmpty()){
            Toast.makeText(BasketActivity.this, R.string.errorNoItems, Toast.LENGTH_SHORT).show();
            return;
        }
        MainController.addOrder(new Order(this.order));
        this.order.setMenuItems(new ArrayList<MenuItem>());
        this.items.clear();
        MainController.clearCurrentOrder();

        subTotal.setText("$0.00");
        salesTax.setText("$0.00");
        totalExpense.setText("$0.00");
        Toast.makeText(BasketActivity.this, R.string.successOrder, Toast.LENGTH_SHORT).show();
    }

    /**
     * This event listener is responsible for deleting an item from the basket or
     * printing out an error message if no item was selected
     * @param v View that was clicked
     */
    public void cancelItemOrder(View v){
        if (this.indexOfRemoveItem == -1) {
            Toast.makeText(BasketActivity.this, R.string.errorCancelItem, Toast.LENGTH_SHORT).show();
            return;
        }
        MenuItem item = (MenuItem) this.ordersListView.getItemAtPosition(this.indexOfRemoveItem);
        //Log.d("Remove item index -- ", String.valueOf(this.indexOfRemoveItem));
        this.items.remove((MenuItem) this.items.getItem(indexOfRemoveItem));
        MainController.removeMenuItem(item);
        for (int i = 0; i<MainController.getCurrentOrder().size(); i++){
            Log.d("Item", MainController.getCurrentOrder().get(i).toString());
        }
        calculatePrice();
        this.indexOfRemoveItem = -1;
        Toast.makeText(BasketActivity.this, R.string.successCancelItem, Toast.LENGTH_SHORT).show();
    }

    /**
     * This helper method is responsible for calculating the current price
     * of the basket and displaying it to the user
     */
    private void calculatePrice(){
        DecimalFormat df = new DecimalFormat("#,##0.00");
        subTotal.setText("$" + df.format(this.order.getPrice()));
        salesTax.setText("$" + df.format(this.order.getPrice() * SALES_TAX));
        totalExpense.setText("$" + df.format(this.order.getPrice() + (this.order.getPrice() * SALES_TAX)));
    }

}