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
        String [] donutTypeNames = {"Yeast-Glazed", "Yeast-Chocolate Frosted" , "Yeast-Cinnamon", "Yeast-Strawberry Frosted",
                "Cake-Velvet", "Cake-Powdered Sugar", "Cake-Extra Chocolate", "Donut Holes-Sprinkled", "Donut Holes-Pumpkin",
        "Donut Holes-Plain"};

        for (int i = 0; i < donutTypeNames.length; i++){
            donutTypes.add(new donutTypeModel(donutTypeNames[i]));
        }

    }

}
