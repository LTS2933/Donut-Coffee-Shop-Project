package com.example.project5cs213;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project5cs213.model.Order;

import java.util.ArrayList;

/**
 * This class is responsible for handling events related to the OrdersActivity such
 * as creating and registering event listeners to cancel an order and also displays
 * the orders to the user
 * @author Christian Osma, Liam Smith
 */
public class OrdersActivity extends AppCompatActivity{

    private static int NONE_SELECTED = -1;

    private Button cancelOrderButton;

    private Button backToMain;

    private ListView ordersListView;

    private int indexOfRemoveOrder;

    private ArrayAdapter<Order> items;

    /**
     * This method is called when the activity is created and is responsible for
     * adding event listeners, instantiating variables, and obtaining the list
     * of orders
     * @param savedInstanceState Bundle object passed into the activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        this.cancelOrderButton = (Button) this.findViewById(R.id.cancelOrderButton);
        this.backToMain = (Button) this.findViewById(R.id.backToMainViewButton);
        this.ordersListView = (ListView) this.findViewById(R.id.storeOrdersListView);
        this.ordersListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3){
                indexOfRemoveOrder = position;
            }
        });
        this.indexOfRemoveOrder = NONE_SELECTED;

        this.items = new ArrayAdapter<Order>(OrdersActivity.this, android.R.layout.simple_list_item_1, MainController.getOrders());
        this.ordersListView.setAdapter(this.items);
    }

    /**
     * This method finishes the current activity and returns to the main screen
     * @param v View that was clicked
     */
    public void backToMainFromOrders(View v){finish();}

    /**
     * This event listener is responsible for cancelling the selected order
     * or detecting an error and displaying a message to the user
     * @param v View that was clicked
     */
    public void cancelSelectedOrder(View v){
        if (this.indexOfRemoveOrder == NONE_SELECTED){
            Toast.makeText(OrdersActivity.this, R.string.errorDeleteOrder, Toast.LENGTH_SHORT).show();
            return;
        }
        Order deleteOrder = (Order) this.ordersListView.getItemAtPosition(this.indexOfRemoveOrder);
        this.items.remove((Order)this.items.getItem(this.indexOfRemoveOrder));
        MainController.removeOrder(deleteOrder);

        Toast.makeText(OrdersActivity.this, R.string.successCancelOrder, Toast.LENGTH_SHORT).show();
        this.indexOfRemoveOrder = NONE_SELECTED;
    }
}