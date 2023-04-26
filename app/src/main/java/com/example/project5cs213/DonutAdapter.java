package com.example.project5cs213;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter that is responsible for connecting the array list of donut models
 * to the recycler view in the user interface
 * @author Christian Osma, Liam Smith
 */
public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.MyViewHolder>{

    Context context;
    ArrayList<DonutTypeModel> donutModels;

    private int selectedPos;

    /**
     * Public constructor that instantiates the context and donutModels array list
     * @param context Context of caller
     * @param donutModels ArrayList of Models containing information about the donuts
     */
    public DonutAdapter(Context context, ArrayList<DonutTypeModel> donutModels){
        this.context = context;
        this.donutModels = donutModels;
        this.selectedPos = -1;
    }

    /**
     * This method inflates the layout or gives a look to the rows
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return ViewHolder containing the layout of an individual row in the list
     */
    @NonNull
    @Override
    public DonutAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new DonutAdapter.MyViewHolder(view);
    }

    /**
     * This method assigns values to the views in the recycler_view_row file based
     * on the position of the row
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull DonutAdapter.MyViewHolder holder, int position) {
        holder.donutType.setText(donutModels.get(position).getType());
        holder.donutFlavor.setText(donutModels.get(position).getFlavor());
        holder.imageView.setImageResource(donutModels.get(position).getImage());
    }

    /**
     * Getter method for getting the size of items to display
     * @return Integer representing the size of donut models list
     */
    @Override
    public int getItemCount() {
        return donutModels.size();
    }

    /**
     * ViewHolder that contains the layout for an individual item
     * in our recycler view for donuts
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView donutType, donutFlavor;

        /**
         * Public constructor for creating a new ViewHolder
         * @param itemView View representing a row in our list
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView5);
            donutType = itemView.findViewById(R.id.textView8);
            donutFlavor = itemView.findViewById(R.id.textView9);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    notifyItemChanged(selectedPos);

                    int position = getAdapterPosition();
                    DonutActivity.indexOfChosenDonut = position;
                    selectedPos = position;

                    notifyItemChanged(selectedPos);
                }
            });
        }
    }
}
