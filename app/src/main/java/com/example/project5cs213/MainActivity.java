package com.example.project5cs213;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.example.project5cs213.Donut;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.donutTypeRecyclerView);

        setUpDonutTypes();

        DonutAdapter donutAdapter = new DonutAdapter(this, donutTypeModel);

        recyclerView.setAdapter(donutAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    ArrayList<donutTypeModel> donutTypes = new ArrayList<>();
        private void setUpDonutTypes() {
            String [] donutTypeNames = getResources().getStringArray(R.array.donut_type_names_txt);

            for (int i = 0; i < donutTypeNames.length; i++){
                    donutTypes.add(new donutTypeModel(donutTypeNames[i]));
            }

        }


}