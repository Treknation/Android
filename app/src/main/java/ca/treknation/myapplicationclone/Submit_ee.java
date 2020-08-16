package ca.treknation.myapplicationclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Submit_ee extends AppCompatActivity {

    private ImageView btnBackArrow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_ee);
        initViews();

        btnBackArrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit_ee.this.finish();
            }
        });
    }

    private void initViews() {
        btnBackArrow2 = findViewById(R.id.btnBackArrow2);
    }
}