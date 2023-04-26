package com.example.project5cs213;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DonutActivity extends AppCompatActivity{

    private Button backToMain;

    ArrayList<donutTypeModel> donutTypes = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.donut_ordering_view);
            this.backToMain = (Button) findViewById(R.id.mainCoffee);
            this.backToMain.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    finish();
                }
            });

        RecyclerView recyclerView = findViewById(R.id.donutTypeRecyclerView);

        setUpDonutTypes();

        DonutAdapter donutAdapter = new DonutAdapter(this, donutTypes);

        recyclerView.setAdapter(donutAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    private void setUpDonutTypes() {
        String [] donutTypeNames = {"Yeast-Glazed", "Yeast-Chocolate Frosted" , "Yeast-Cinnamon", "Yeast-Strawberry Frosted", "Yeast-Vanilla Frosted",
                "Cake-Velvet", "Cake-Powdered Sugar", "Cake-Extra Chocolate", "Cake-Jelly", "Donut Holes-Sprinkled", "Donut Holes-Pumpkin",
        "Donut Holes-Plain"};

        int [] donutTypeImages = {R.drawable.yeast_glazed, R.drawable.yeast_chocolate_frosted, R.drawable.yeast_cinnamon,
                R.drawable.yeast_strawberry_frosted, R.drawable.yeast_vanilla_frosted, R.drawable.cake_velvet, R.drawable.cake_powdered_sugar, R.drawable.cake_extra_chocolate,
                R.drawable.cake_jelly, R.drawable.donutholes_sprinkled, R.drawable.donutholes_pumpkin, R.drawable.donutholes_plain};


        for (int i = 0; i < donutTypeNames.length; i++){
            donutTypes.add(new donutTypeModel(donutTypeNames[i], donutTypeImages[i]));
        }

    }

}
