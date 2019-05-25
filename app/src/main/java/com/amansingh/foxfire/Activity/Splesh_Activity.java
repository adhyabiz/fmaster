package com.amansingh.foxfire.Activity;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.amansingh.foxfire.R;

public class Splesh_Activity extends AppCompatActivity {

    private ImageView imageView;
    private final int SPLASH_DISPLAY_LENGTH = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh_);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splesh_Activity.this,WelcomePage_Activity.class);
                Splesh_Activity.this.startActivity(mainIntent);
                Splesh_Activity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
