package ca.treknation.myapplicationclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LanguageCosts extends AppCompatActivity {
    private ImageView btnBackArrow5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_costs);
        initViews();

        btnBackArrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageCosts.this.finish();
            }
        });

    }

    private void initViews() {
        btnBackArrow5 = findViewById(R.id.btnBackArrow5);
    }
}