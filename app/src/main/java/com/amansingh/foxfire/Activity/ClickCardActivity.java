package com.amansingh.foxfire.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amansingh.foxfire.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClickCardActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @BindView(R.id.toolBar_menu)
    ImageView toolBarMenu;
    @BindView(R.id.userTV)
    TextView userTV;
    @BindView(R.id.locationTV)
    TextView locationTV;
    @BindView(R.id.speedTV)
    TextView speedTV;
    @BindView(R.id.masterTV)
    TextView masterTV;
    @BindView(R.id.engineTV)
    TextView engineTV;

    private String user_id, master_id;
    private final String TAG = "ClickCard";
    private String lat, lng, mTitle;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click__card_);
        ButterKnife.bind(this);

        toolBarMenu.setOnClickListener(v -> Showpopup(toolBarMenu));
        Bundle bundle = getIntent().getBundleExtra("Data");
        user_id = bundle.getString("user_id");
        master_id = bundle.getString("master_id");
        Log.e("User card", "onCreate: bundle user " + bundle.getString("user_id"));

        userTV.setText("User: " + user_id);
        masterTV.setText("Master: " + master_id);

        getFirebaseData();
    }

    @SuppressLint("SetTextI18n")
    private void getFirebaseData() {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("Users").document(user_id)
                .addSnapshotListener((documentSnapshot, e) -> {
                    if (documentSnapshot.exists()) {
                        String speed = documentSnapshot.getString("speed");
                        String engine = documentSnapshot.getString("start");
                        HashMap<String, Object> map;
                        map = (HashMap<String, Object>) documentSnapshot.get("location");
                        Log.e(TAG, "getFirebaseData: map " + Objects.requireNonNull(map).toString());
                        Log.e(TAG, "getFirebaseData: speed " + speed);
                        lat = map.get("lat").toString();
                        lng = map.get("long").toString();
                        mTitle = "Device";
                        speedTV.setText("User Speed: " + speed);
                        locationTV.setText("Location: Click to view");
                        engineTV.setText("Engine " + engine);
                    }
                });
    }

    public void Showpopup(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(ClickCardActivity.this, AudioRecorder.class);
                startActivity(intent);
                Toast.makeText(this, "AudioRecorder", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                String action;
                Intent intent1 = new Intent(ClickCardActivity.this, VideoRecorder.class);
                startActivity(intent1);
                Toast.makeText(this, "VideoRecorder", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Intent intent2 = new Intent(ClickCardActivity.this, ScreenShot.class);
                startActivity(intent2);
                Toast.makeText(this, "ScreenShot", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    @OnClick(R.id.locationTV)
    public void onViewClicked() {
        String url = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + mTitle + ")";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
