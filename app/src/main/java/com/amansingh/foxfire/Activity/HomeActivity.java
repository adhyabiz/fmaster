package com.amansingh.foxfire.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amansingh.foxfire.Adapters.HomeRecycler;
import com.amansingh.foxfire.Models.HomeListModel;
import com.amansingh.foxfire.Models.Utils;
import com.amansingh.foxfire.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    private final String TAG = "HomeActivity";
    @BindView(R.id.logOutBtn)
    Button logOutBtn;
    private ArrayList<HomeListModel> userList;
    private String master_id;
    private List<String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        ButterKnife.bind(this);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPrefMaster", MODE_PRIVATE);
        String user_id = pref.getString("user", "null");  // getting user_id
        master_id = pref.getString("master", "111");  // getting master_id

        users = new ArrayList<>();

        String[] arrSplit = Objects.requireNonNull(user_id).split(",");
        Collections.addAll(users, arrSplit);
        Log.e(TAG, "onCreate: user id " + users);

        userList = new ArrayList<>();
        firebaseData();
        firebaseToken();
    }

    private void firebaseData() {
        Log.e(TAG, "firebaseData: firebaseData entered ");
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Query query = firestore.collection("Users").whereEqualTo("master_id", master_id);
        try {
            query.addSnapshotListener(this, (queryDocumentSnapshots, e) -> {
                if (!Objects.requireNonNull(queryDocumentSnapshots).isEmpty()) {
                    for (final DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {
                            String master_id = doc.getDocument().getId();
                            final HomeListModel list = doc.getDocument().toObject(HomeListModel.class).withID(master_id);
                            userList.add(list);
                            Log.e(TAG, "firebaseData: list size " + userList.size());
                        } else {
                            Log.e(TAG, "firebaseData: docType else ");
                        }
                    }
                    Log.e(TAG, "firebaseData: outside for loop ");
                    Log.e(TAG, "firebaseData: after Query list size " + userList.size());
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    HomeRecycler adapter = new HomeRecycler(userList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e(TAG, "firebaseData: doc is empty");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "firebaseData: exception " + e.getMessage());
        }
    }

    private void firebaseToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "getInstanceId failed", task.getException());
                        return;
                    }

                    // Get new Instance ID token
                    String token = Objects.requireNonNull(task.getResult()).getToken();
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
                        Log.e(TAG, "addTokenToFirebase: master data error " + Objects.requireNonNull(task.getException()).getMessage());
                        //github.com/iRahulGaur
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
                        Log.e(TAG, "addTokenToFirebase: user data error " + Objects.requireNonNull(task.getException()).getMessage());
                    }
                });
    }

    @OnClick(R.id.logOutBtn)
    public void onViewClicked() {
        logOut();
    }

    private void logOut() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPrefMaster", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
        Log.e("SettingsActivity", "logOut: ");
        Utils.setIntentNoBackLog(HomeActivity.this, WelcomePageActivity.class);
    }
}
