package ca.treknation.myapplicationclone;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Cec extends AppCompatActivity {
    private ImageView btnBackArrow6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cec);
        initViews();
        btnBackArrow6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cec.this.finish();
            }
        });
    }

    private void initViews() {
        btnBackArrow6 = findViewById(R.id.btnBackArrow6);
    }
}