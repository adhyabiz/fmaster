package com.amansingh.foxfire.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amansingh.foxfire.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Click_Card_Activity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @BindView(R.id.toolBar_menu)
    ImageView toolBarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click__card_);
        ButterKnife.bind(this);

        toolBarMenu.setOnClickListener(v -> Showpopup(toolBarMenu));
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
                Intent intent=new Intent(Click_Card_Activity.this,Audio_Recoder.class);
                startActivity(intent);
                Toast.makeText(this, "Audio_Recoder", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                String action;
                Intent intent1=new Intent(Click_Card_Activity.this,Video_Recorder.class);
                startActivity(intent1);
                Toast.makeText(this, "Video_Recorder", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Intent intent2=new Intent(Click_Card_Activity.this,ScreenShot.class);
                startActivity(intent2);
                Toast.makeText(this, "ScreenShot", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}
