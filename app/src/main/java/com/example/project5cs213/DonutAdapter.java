package com.example.project5cs213;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.MyViewHolder> {
    Context context;
    ArrayList<donutTypeModel> donutTypeModels;

    public DonutAdapter(Context context, ArrayList<donutTypeModel> donutTypeModels) {
        this.context = context;
        this.donutTypeModels = donutTypeModels;
    }
    @NonNull
    @Override
    public DonutAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donut_ordering_view, parent, false);
        return new DonutAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonutAdapter.MyViewHolder holder, int position){
        holder.donutType.setText(donutTypeModels.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return donutTypeModels.size();
    }

    public static class MyViewHolder extends RecyclerView.MyViewHolder {

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

        }

    }
}
