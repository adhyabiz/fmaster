package com.amansingh.foxfire.Adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amansingh.foxfire.Activity.Click_Card_Activity;
import com.amansingh.foxfire.Models.HomeListModel;
import com.amansingh.foxfire.R;

import java.util.ArrayList;

public class HomeRecycler extends RecyclerView.Adapter{

    ArrayList<HomeListModel>homeListModelArrayList=new ArrayList<>();
    Context context;

    public HomeRecycler(ArrayList<HomeListModel> homeListModelArrayList, Context context) {
        this.homeListModelArrayList = homeListModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_custom,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeListModel homeListModel=homeListModelArrayList.get(position);
        ((ViewHolder)holder).textView.setText(homeListModel.getText());
        ((ViewHolder)holder).textView1.setText(homeListModel.getName());
        ((ViewHolder)holder).textView2.setText(homeListModel.getLocation());

    }

    @Override
    public int getItemCount() {
        return homeListModelArrayList.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView,textView1,textView2;
        CardView cardView;
        Button button;

         ViewHolder( View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textView);
            textView1=itemView.findViewById(R.id.textView1);
            textView2=itemView.findViewById(R.id.textView2);
            cardView=itemView.findViewById(R.id.cardView);
            button=itemView.findViewById(R.id.button);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,Click_Card_Activity.class);
                    context.startActivity(intent);


                }
            });
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent=new Intent(context,Settings_Activity.class);
//                    context.startActivity(intent);
//                }
//            });



        }
    }
}
