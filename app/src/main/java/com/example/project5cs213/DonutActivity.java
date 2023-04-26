package com.example.project5cs213;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project5cs213.model.*;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class is responsible for handling events relating to the DonutActivity
 * such as creating and registering event listeners to place a donut order
 * and cancel a donut order
 * @author Christian Osma, Liam Smith
 */
public class DonutActivity extends AppCompatActivity{

    private TextInputEditText quantityEditText;

    private TextInputEditText subtotalEditText;

    private ListView ordersListView;

    private RecyclerView donutsRecyclerView;

    private ArrayAdapter<Donut> donuts;

    private int indexOfRemoveItem;

    public static int indexOfChosenDonut = -1;

    private ArrayList<DonutTypeModel> donutModels = new ArrayList<>();


    /**
     * This method populates the array list of DonutTypeModel's with the information
     * for the 12 donuts in the RecyclerView
     */
    private void setUpDonutModels(){
        String [] types = getResources().getStringArray(R.array.donutTypes);
        String [] flavors = getResources().getStringArray(R.array.donutFlavors);
        int [] donutTypeImages = {R.drawable.yeast_glazed, R.drawable.yeast_chocolate_frosted, R.drawable.yeast_cinnamon,
                R.drawable.yeast_strawberry_frosted, R.drawable.yeast_vanilla_frosted, R.drawable.cake_velvet, R.drawable.cake_powdered_sugar, R.drawable.cake_extra_chocolate,
                R.drawable.cake_jelly, R.drawable.donutholes_sprinkled, R.drawable.donutholes_pumpkin, R.drawable.donutholes_plain};
        for (int i = 0; i<types.length; i++){
            donutModels.add(new DonutTypeModel(types[i], flavors[i], donutTypeImages[i]));
        }
    }

    /**
     * This method is called when the activity is created and is responsible for
     * instantiating variables, setting the content view, adding event listeners,
     * and adding adapters
     * @param savedInstanceState Bundle object that was passed into the activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        this.quantityEditText = (TextInputEditText) this.findViewById(R.id.quantityEditText);
        this.subtotalEditText = (TextInputEditText) this.findViewById(R.id.subtotalEditText);
        this.ordersListView = (ListView) this.findViewById(R.id.ordersListView);
        this.donutsRecyclerView = (RecyclerView) this.findViewById(R.id.donutsRecyclerView);
        this.ordersListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3){
                indexOfRemoveItem = position;
            }
        });
        this.donuts = new ArrayAdapter<Donut>(DonutActivity.this, android.R.layout.simple_list_item_1, new ArrayList<Donut>());
        this.ordersListView.setAdapter(this.donuts);
        this.indexOfRemoveItem = -1;

        setUpDonutModels();
        DonutAdapter donutAdapter = new DonutAdapter(this, donutModels);
        for (int i = 0; i<donutModels.size(); i++){
            Log.i("Created Model", donutModels.get(i).toString());
        }
        donutsRecyclerView.setAdapter(donutAdapter);
        donutsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * This event listener is responsible for adding a donut order to the basket
     * and displaying a success message or detecting an error and displaying an
     * error messgae
     * @param v View that was clicked
     */
    public void handleOrderDonuts(View v){
        String stringQuantity = String.valueOf(quantityEditText.getText());
        int quantity = 0;
        try {quantity = Integer.parseInt(stringQuantity);}
        catch (Exception e){
            Toast.makeText(DonutActivity.this, R.string.errorEnterQuantity, Toast.LENGTH_SHORT).show();
            return;
        }
        if (indexOfChosenDonut == -1){
            Toast.makeText(DonutActivity.this, R.string.errorSelectDonut, Toast.LENGTH_SHORT).show();
            return;
        }

        DonutTypeModel chosenDonut = this.donutModels.get(indexOfChosenDonut);
        Donut newDonut = new Donut(chosenDonut.getType(), chosenDonut.getFlavor(), quantity);
        MainController.addMenuItem(newDonut);
        this.donuts.add(newDonut);

        price();
        indexOfChosenDonut = -1;
        Toast.makeText(DonutActivity.this, R.string.successAddDonut, Toast.LENGTH_SHORT).show();
    }

    /**
     * This event listener is responsible for deleting a donut from the donut orders
     * or detecting an error
     * @param v View that was clicked
     */
    public void handleDeleteDonut(View v){
        if (this.donuts.getCount() == 0){
            Toast.makeText(DonutActivity.this, R.string.errorNoDonuts, Toast.LENGTH_SHORT).show();
            return;
        }
        if (this.indexOfRemoveItem == -1) {
            Toast.makeText(DonutActivity.this, R.string.errorRemoveOrder, Toast.LENGTH_SHORT).show();
            return;
        }
        MainController.removeMenuItem(this.donuts.getItem(indexOfRemoveItem));
        this.donuts.remove((Donut) this.donuts.getItem(indexOfRemoveItem));
        this.indexOfRemoveItem = -1;
        price();
        Toast.makeText(DonutActivity.this, R.string.successRemoveDonutOrder, Toast.LENGTH_SHORT).show();
    }

    /**
     * Helper method that calculates the price of the donut orders and displays
     * it in the sub total text on the screen
     */
    private void price(){
        double price = 0.0;
        for (int i = 0; i<donuts.getCount(); i++){
            price += donuts.getItem(i).getPrice();
        }
        DecimalFormat df = new DecimalFormat("#,##0.00");
        subtotalEditText.setText("$" + df.format(price));
    }

    /**
     * This method finishes the current activity and returns back to the main screen
     * @param v
     */
    public void backToMainFromDonuts(View v){finish();}
}