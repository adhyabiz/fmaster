package com.amansingh.foxfire.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amansingh.foxfire.Adapters.HomeRecycler;
import com.amansingh.foxfire.Models.HomeListModel;
import com.amansingh.foxfire.R;
import com.amansingh.foxfire.Services.Utils;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private final String TAG = "HomeActivity";
    HomeRecycler homeRecycler;
    ImageView button;
    ArrayList<HomeListModel> homeListModelArrayList = new ArrayList<>();
    private String master_id;
    private List<String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.button);

        master_id = "111";
        users = new ArrayList<>();
        users.add("11");
        users.add("10");

        homeListModelArrayList.clear();
        HomeListModel homeListModel;
        homeListModel = new HomeListModel("User 1", "name", "location");
        homeListModelArrayList.add(homeListModel);
        homeListModel = new HomeListModel("2", "name", "location");
        homeListModelArrayList.add(homeListModel);
        homeRecycler = new HomeRecycler(homeListModelArrayList, getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(homeRecycler);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        firebaseToken();
    }

    private void firebaseToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "getInstanceId failed", task.getException());
                        return;
                    }

                    // Get new Instance ID token
                    String token = task.getResult().getToken();
                    addTokenToFirebase(token);

                    // Log and toast
                    Log.e(TAG, "onComplete: token " + token);
                });
        String token = Utils.TOKEN;
        Log.e(TAG, "firebaseToken: token = " + token);

    }

    private void addTokenToFirebase(String token) {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("users", users);
        firestore.collection("Master").document(master_id).set(map)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.e(TAG, "addTokenToFirebase: master data uploaded ");
                    } else {
                        Log.e(TAG, "addTokenToFirebase: master data error " + task.getException().getMessage());
                    }
                });
        updateToken(token, users.get(0));
    }

    private void updateToken(String token, String user) {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("master_id", master_id);
        firestore.collection("Users").document(user).update(map)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.e(TAG, "addTokenToFirebase: user data uploaded ");
                    } else {
                        Log.e(TAG, "addTokenToFirebase: user data error " + task.getException().getMessage());
                    }
                });
    }
}
