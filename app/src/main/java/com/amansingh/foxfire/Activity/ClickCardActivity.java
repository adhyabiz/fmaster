package com.amansingh.foxfire.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private String user_id, master_id;

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

    private void getFirebaseData() {

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
}
