package ca.treknation.myapplicationclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Fst extends AppCompatActivity {
    private ImageView btnBackArrow7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fst);
        initViews();
        btnBackArrow7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fst.this.finish();
            }
        });
    }

    private void initViews() {
        btnBackArrow7 = findViewById(R.id.btnBackArrow7);
    }
}