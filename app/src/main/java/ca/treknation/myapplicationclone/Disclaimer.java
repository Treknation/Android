package ca.treknation.myapplicationclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Disclaimer extends AppCompatActivity {

    private ImageView btnBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        initViews();

        btnBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disclaimer.this.finish();
            }
        });


    }

    private void initViews() {
        btnBackArrow = findViewById(R.id.btnBackArrow);

    }
}