package com.amansingh.foxfire.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amansingh.foxfire.Activity.ClickCardActivity;
import com.amansingh.foxfire.Models.HomeListModel;
import com.amansingh.foxfire.Models.Utils;
import com.amansingh.foxfire.R;

import java.util.ArrayList;

public class HomeRecycler extends RecyclerView.Adapter<HomeRecycler.ViewHolder> {

    private ArrayList<HomeListModel> userList;
    private Context context;

    public HomeRecycler(ArrayList<HomeListModel> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_custom, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String TAG = "HomeRecycler";
        Bundle b = new Bundle();
        try {
            String user_id = userList.get(position).getUser_id();
            String speed = userList.get(position).getSpeed();
            String geo = userList.get(position).getGeoFencing();
            String start = userList.get(position).getStart();
            b.putString("user_id", userList.get(position).getUser_id());
            b.putString("master_id", userList.get(position).getMaster_id());
            Log.e(TAG, "onBindViewHolder: " + speed + " " + geo + " " + start);
            if (user_id != null) holder.userTV.setText(user_id);
            if (speed != null) holder.speedTV.setText(speed);
            if (geo != null) holder.geoTV.setText(geo.toUpperCase() + " Fencing");
            if (start != null) holder.OnOffTV.setText("Engine " + start);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "onBindViewHolder: exception " + e);
        }
        Log.e(TAG, "onBindViewHolder: list size " + userList.size());

        //on card click open the user
        holder.cardView.setOnClickListener
                (v -> Utils.setIntentExtra(
                        context,
                        ClickCardActivity.class,
                        "Data",
                        b
                ));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView userTV, locationTV, speedTV, geoTV, OnOffTV;
        CardView cardView;
        View view;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            userTV = view.findViewById(R.id.user_row);
            locationTV = view.findViewById(R.id.locationTV);
            speedTV = view.findViewById(R.id.speedTV);
            geoTV = view.findViewById(R.id.geoTV);
            OnOffTV = view.findViewById(R.id.on_off_TV);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}
