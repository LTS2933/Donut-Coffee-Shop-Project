package com.example.project5cs213;


import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project5cs213.model.*;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Console;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling events related to the CoffeeActivity such as
 * creating and registering event listeners to place a coffee order and display the
 * subtotal to the user
 * @author Christian Osma, Liam Smith
 */
public class CoffeeActivity extends AppCompatActivity{

    private CheckBox sweetCream;
    private CheckBox frenchVanilla;
    private CheckBox irishCream;
    private CheckBox caramel;
    private CheckBox mocha;
    private TextInputEditText orderQuantity;
    private TextInputEditText subTotal;
    private Spinner coffeeSizes;
    private Coffee coffee;
    private List<String> addOns;
    String [] arraySizes = new String[]{"...", "SMALL", "TALL", "GRANDE", "VENTI"};

    /**
     * This method is called when the activity is created and is responsible for instantiating
     * variables, setting the content view, adding adapters, and registering handlers
     * @param savedInstanceState Bundle object passed into the activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        sweetCream = (CheckBox) this.findViewById(R.id.sweetCreamCheckBox);
        frenchVanilla = (CheckBox) this.findViewById(R.id.frenchVanillaCheckBox);
        irishCream = (CheckBox) this.findViewById(R.id.irishCreamCheckBox);
        caramel = (CheckBox) this.findViewById(R.id.caramelCheckBox);
        mocha = (CheckBox) this.findViewById(R.id.mochaCheckBox);
        orderQuantity = (TextInputEditText) this.findViewById(R.id.coffeeQuantityEditText);
        subTotal = (TextInputEditText)this.findViewById(R.id.coffeeSubtotalEditText);
        coffeeSizes = (Spinner) this.findViewById(R.id.coffeeSizeSpinner);

        ArrayAdapter<String> sizesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySizes);
        coffeeSizes.setAdapter(sizesAdapter);
        coffee = new Coffee();
        addOns = new ArrayList<String>();
        registerHandlers();
        this.subTotal.setText("$0.00");
    }

    /**
     * This helper method adds event listeners to some of the Views in the xml file
     * to add functionality to the activity
     */
    private void registerHandlers(){
        this.coffeeSizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {calculatePrice();}
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
        this.sweetCream.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){calculatePrice();}
        });
        this.frenchVanilla.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){calculatePrice();}
        });
        this.irishCream.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){calculatePrice();}
        });
        this.caramel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){calculatePrice();}
        });
        this.mocha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){calculatePrice();}
        });
        this.orderQuantity.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {calculatePrice();}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {calculatePrice();}
        });
    }

    /**
     * This helper method is responsible for calculating the price of the coffee
     * order and showing it to the user
     */
    private void calculatePrice(){
        if (coffeeSizes.getSelectedItemPosition() == 0) return;
        String size = arraySizes[coffeeSizes.getSelectedItemPosition()];
        String stringQuantity = String.valueOf(orderQuantity.getText());
        int quantity = 0;
        try {quantity = Integer.parseInt(stringQuantity);}
        catch (Exception e){return;}

        if (quantity > 5 || quantity <= 0) return;
        if (sweetCream.isChecked()) this.addOns.add("SWEET CREAM");
        if (frenchVanilla.isChecked()) this.addOns.add("FRENCH VANILLA");
        if (irishCream.isChecked()) this.addOns.add("IRISH CREAM");
        if (caramel.isChecked()) this.addOns.add("CARAMEL");
        if (mocha.isChecked()) this.addOns.add("MOCHA");

        coffee.setQuantity(quantity);
        coffee.setSize(size);
        coffee.setToppings(this.addOns);

        DecimalFormat df = new DecimalFormat("#,##0.00");
        double price = coffee.getPrice();
        subTotal.setText("$" + df.format(price));
        coffee.clearToppings();
    }

    /**
     * This event listener is responsible for adding the coffee order to the basket
     * or detecting an error and displaying it to the user
     * @param v View that was clicked
     */
    public void handleOrder(View v){
        if (coffeeSizes.getSelectedItemPosition() == 0){
            Toast.makeText(CoffeeActivity.this, R.string.errorEnterSize, Toast.LENGTH_SHORT).show();
            return;
        }
        String size = arraySizes[coffeeSizes.getSelectedItemPosition()];
        String stringQuantity = String.valueOf(orderQuantity.getText());
        int quantity = 0;
        try {quantity = Integer.parseInt(stringQuantity);}
        catch (Exception e){
            Toast.makeText(CoffeeActivity.this, R.string.errorEnterQuantity, Toast.LENGTH_SHORT).show();
            return;
        }
        if (quantity > 5 || quantity <= 0) {
            Toast.makeText(CoffeeActivity.this, R.string.errorInvalidQuantity, Toast.LENGTH_SHORT).show();
            return;
        }
        if (sweetCream.isChecked()) this.addOns.add("SWEET CREAM");
        if (frenchVanilla.isChecked()) this.addOns.add("FRENCH VANILLA");
        if (irishCream.isChecked()) this.addOns.add("IRISH CREAM");
        if (caramel.isChecked()) this.addOns.add("CARAMEL");
        if (mocha.isChecked()) this.addOns.add("MOCHA");

        coffee.setSize(size);
        coffee.setQuantity(quantity);
        List<String> copy = new ArrayList<String>();
        for (String item : this.addOns){copy.add(item);}
        coffee.setToppings(copy);

        DecimalFormat df = new DecimalFormat("#,##0.00");
        double price = coffee.getPrice();
        subTotal.setText("$" + df.format(price));
        handleAlert();
    }

    /**
     * This helper method is responsible for actually adding the coffee order
     * to the basket
     */
    private void addOrder(){
        MainController.addMenuItem(new Coffee(this.coffee));
        this.addOns.clear();
        clearParameters();
    }

    /**
     * This method is responsible for showing an AlertDialog to the user when they
     * want to place an coffee order. If the user confirms they wants to add an order,
     * it calls addOrder() to do so and if not, then it cancels the order
     */
    private void handleAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Place Order");
        alert.setMessage("Are you sure you want to place this order?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addOrder();
                Toast.makeText(CoffeeActivity.this, R.string.successAddCoffee, Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                Toast.makeText(CoffeeActivity.this, R.string.cancelCoffeeOrder, Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }

    /**
     * This helper method is used to set all parameters to its default value
     */
    private void clearParameters(){
        sweetCream.setChecked(false);
        frenchVanilla.setChecked(false);
        irishCream.setChecked(false);
        caramel.setChecked(false);
        mocha.setChecked(false);
        orderQuantity.setText("");
        subTotal.setText("");
        coffeeSizes.setSelection(0);
    }

    /**
     * Event listener that is called when the user wants to cancel their order
     * @param v View that was clicked
     */
    public void handleCancel(View v){
        clearParameters();
    }

    /**
     * This method finishes the current activity and returns to the main screen
     * @param v View that was clicked
     */
    public void backToMainScreenFromCoffee(View v){finish();}

}