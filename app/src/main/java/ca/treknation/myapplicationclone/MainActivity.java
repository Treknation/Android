package ca.treknation.myapplicationclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private static RecyclerView itemRecView;
    private TextView txtTrekNation;
    private ImageView home_logo;
    private Context pContext;
    private static final int MY_REQUEST = 1001;
    private ArrayList<Item> itemList;
    private itemRecViewAdapter adapter;
    BottomNavigationView bottomNavigationView;
    Fragment mainFragment;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setItemIconTintList(null);
        itemRecView = findViewById(R.id.itemRecView);
        txtTrekNation = findViewById(R.id.txtTrekNation);
        home_logo = findViewById(R.id.home_logo);


        itemList = new ArrayList<>();
        adapter = new itemRecViewAdapter(this);
        itemRecView.setAdapter(adapter);
        itemRecView.setLayoutManager(new LinearLayoutManager(this));
        itemList = Utils.getInstance().getAllItems();
        adapter.setItems(itemList);
        initView();
        initBottomNavView();


//        bottomNavigationView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch(bottomNavigationView.getSelectedItemId()) {
//
//                }
//            }
//        });


    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottomNavView);
    }

    private void initBottomNavView() {
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        break;
                    case R.id.other:
                        Intent intent = new Intent(MainActivity.this, MoreActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("Item ID", 0);
                int position = data.getIntExtra("Position", 0);
                itemList.get(position).isViewed = true;
                adapter.setItems(itemList);
            }
        }
    }


}