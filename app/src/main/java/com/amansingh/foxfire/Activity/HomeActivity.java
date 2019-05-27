package com.amansingh.foxfire.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amansingh.foxfire.Adapters.HomeRecycler;
import com.amansingh.foxfire.Models.HomeListModel;
import com.amansingh.foxfire.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<HomeListModel>homeListModelArrayList=new ArrayList<>();
    HomeRecycler homeRecycler;
    ImageView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        recyclerView=findViewById(R.id.recyclerView);
        button=findViewById(R.id.button);
        homeListModelArrayList.clear();
        HomeListModel homeListModel;
        homeListModel=new HomeListModel("User 1","name","location");
        homeListModelArrayList.add(homeListModel);
        homeListModel=new HomeListModel("2","name","locatin");
        homeListModelArrayList.add(homeListModel);
        homeRecycler=new HomeRecycler(homeListModelArrayList,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(homeRecycler);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
