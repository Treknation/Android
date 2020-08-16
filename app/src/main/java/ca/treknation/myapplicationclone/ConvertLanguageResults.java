package ca.treknation.myapplicationclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ConvertLanguageResults extends AppCompatActivity {

    private ImageView btnBackArrow3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_lanugage_results);
        initViews();

        btnBackArrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertLanguageResults.this.finish();
            }
        });
    }

    private void initViews() {
        btnBackArrow3 = findViewById(R.id.btnBackArrow3);
    }
}