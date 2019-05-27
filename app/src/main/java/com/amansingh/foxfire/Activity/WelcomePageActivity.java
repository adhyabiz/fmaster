package com.amansingh.foxfire.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.amansingh.foxfire.R;

public class WelcomePageActivity extends AppCompatActivity {

    private EditText editText,editText1;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_);

        editText=findViewById(R.id.editText);
        editText1=findViewById(R.id.editText1);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomePageActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
