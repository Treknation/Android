package ca.treknation.myapplicationclone;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView itemRecView;
    private TextView txtTrekNation;
    private ImageView home_logo;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemRecView = findViewById(R.id.itemRecView);
        txtTrekNation = findViewById(R.id.txtTrekNation);
        home_logo = findViewById(R.id.home_logo);


        itemRecViewAdapter adapter = new itemRecViewAdapter(this);
        itemRecView.setAdapter(adapter);
        itemRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setItems(Utils.getInstance().getAllItems());
    }

}