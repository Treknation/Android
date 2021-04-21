package com.prod.treknation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Feedback extends AppCompatActivity {

    private ImageView btnBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initViews();

        btnBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Feedback.this.finish();
            }
        });
    }

    private void initViews() {
        btnBackArrow = findViewById(R.id.btnBackArrow);

    }
}