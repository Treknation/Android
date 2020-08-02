package ca.treknation.myapplicationclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class LongDesc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_desc);
        Toast.makeText(this, "Long Description Clicked", Toast.LENGTH_SHORT).show();
    }
}