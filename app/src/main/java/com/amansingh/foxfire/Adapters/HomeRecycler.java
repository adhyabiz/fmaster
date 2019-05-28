package com.amansingh.foxfire.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amansingh.foxfire.Models.HomeListModel;
import com.amansingh.foxfire.R;

import java.util.ArrayList;

public class HomeRecycler extends RecyclerView.Adapter<HomeRecycler.ViewHolder> {

    private ArrayList<HomeListModel> homeListModelArrayList;
    private Context context;
    private final String TAG = "HomeRecycler";

    public HomeRecycler(ArrayList<HomeListModel> homeListModelArrayList) {
        this.homeListModelArrayList = homeListModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_custom, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String user_id = homeListModelArrayList.get(position).getUser_id();
        holder.userTV.setText(user_id);
        Log.e(TAG, "onBindViewHolder: list size " + homeListModelArrayList.size());
    }

    @Override
    public int getItemCount() {
        return homeListModelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView userTV, locationTV, speedTV, geoTV;
        CardView cardView;
        View view;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            userTV = view.findViewById(R.id.user_row);
            locationTV = view.findViewById(R.id.locationTV);
            speedTV = view.findViewById(R.id.speedTV);
            geoTV = view.findViewById(R.id.geoTV);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}
