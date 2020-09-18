package ca.treknation.myapplicationclone;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Dashboard extends AppCompatActivity {

    // Main
    private static final String TAG = "TAG";
    private static RecyclerView itemRecView;
    private TextView txtTrekNation;
    private ImageView home_logo;
    private Context pContext;
    private static final int MY_REQUEST = 1001;
    private ArrayList<Item> itemList;
    private RecycleViewItemListAdapter adapter;
    BottomNavigationView bottomNavigationView;
    Fragment mainFragment;
    private View includeMain;
    private View includeMore;

    //More
    private TextView txtAboutUs, txtFeedback, txtDisclaimer, txtPrivacyPolicy, txtCicContactInformation;

    private static final String TAG1 = "TAG";
    String[] addresses = new String[]{"support@treknation.ca"};
    int[] savedList = new int[13];

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();

        itemRecView = findViewById(R.id.itemRecView);
        txtTrekNation = findViewById(R.id.txtTrekNation);
        home_logo = findViewById(R.id.home_logo);

        itemList = new ArrayList<>();

        //Todo dummy code of adapter
        itemList = Utils.getInstance().getAllItems();

        adapter = new RecycleViewItemListAdapter(this, itemList);
        itemRecView.setAdapter(adapter);
        itemRecView.setLayoutManager(new LinearLayoutManager(this));
//        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
//        String savedString = sharedPreferences.getString("string", "");
//        StringTokenizer st = new StringTokenizer(savedString, ",");

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        savedList = gson.fromJson(json, type);

        if (savedList == null) {
            savedList = new int[0];
        }

//        for(int i=0; i<savedString.length(); i++){
//            savedList[i] = Integer.parseInt(st.nextToken());
//        }
//
        for (int i = 0; i < savedList.length; i++) {
            itemList.get(savedList[i]).isViewed = true;
        }
        //for loop {count according to array.length}
        // if pos = itemList(pos){
        // isViewed = true

        //Todo original code of adapter
        /*adapter = new itemRecViewAdapter(this);
        itemRecView.setAdapter(adapter);
        itemRecView.setLayoutManager(new LinearLayoutManager(this));
        itemList = Utils.getInstance().getAllItems();
        //for loop {count according to array.length}
        // if pos = itemList(pos){
        // isViewed = true
        adapter.setItems(itemList);*/

        //More
        txtAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, AboutUs.class);
                startActivity(intent);
            }
        });

        txtFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "support@treknation.ca", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "User Feedback");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        txtDisclaimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Disclaimer.class);
                startActivity(intent);
            }
        });

        txtPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, PrivacyPolicy.class);
                startActivity(intent);
            }
        });

        txtCicContactInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, CicContact.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottomNavView);
        txtAboutUs = findViewById(R.id.txtAboutUs);
        txtFeedback = findViewById(R.id.txtFeedback);
        txtDisclaimer = findViewById(R.id.txtDisclaimer);
        txtPrivacyPolicy = findViewById(R.id.txtPrivacyPolicy);
        txtCicContactInformation = findViewById(R.id.txtCicContactInformation);

        includeMain = findViewById(R.id.lyt_main);
        includeMore = findViewById(R.id.lyt_more);
       /* mainLyt = includeMain.findViewById(R.id.main_activity);
        moreLyt = includeMore.findViewById(R.id.more_activity);*/

        initBottomNavView();
    }

    private void initBottomNavView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        includeMain.setVisibility(View.VISIBLE);
                        includeMore.setVisibility(View.GONE);
                        break;
                    case R.id.other:
                        includeMain.setVisibility(View.GONE);
                        includeMore.setVisibility(View.VISIBLE);
                        break;
                }
                return true;
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "onActivityResult: Dashboard activity started purav");
                int result = data.getIntExtra("Item ID", 0);
                int position = data.getIntExtra("Position", 0);
                itemList.get(position).isViewed = true;
                adapter.notifyDataSetChanged();
            }
        }
    }
}