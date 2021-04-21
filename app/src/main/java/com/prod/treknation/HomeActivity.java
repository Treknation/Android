package com.prod.treknation;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.prod.treknation.adapter.StepsAdapter;

import static com.prod.treknation.LongDesc.ITEM_ID_KEY;
import static com.prod.treknation.MainActivity.SHARED_PREFS;
import static com.prod.treknation.OnBoardingFragment.IS_FIRST_TIME;

public class HomeActivity extends AppCompatActivity {

    // Main
    private static final String TAG = "TAG";
    private static RecyclerView itemRecView;
    private TextView txtTrekNation;
    private ImageView home_logo;
    private View stepsRecView;
    private Context pContext;
    private static final int MY_REQUEST = 1001;
    private ArrayList<Item> itemList;
    private ArrayList<Item> itemListSteps = new ArrayList<>();

    private RecycleViewItemListAdapter adapter;
    BottomNavigationView bottomNavigationView;
    Fragment mainFragment;
    private View includeMain;
    private View includeMore;
    private View includeDashboard;
    public static final String USERSTAGE = "userstage";

    //More
    private TextView txtAboutUs, txtFeedback, txtDisclaimer, txtPrivacyPolicy, mTxtCommonMistakes, txtCicContactInformation, txtSellAll, txtStartYourJourney, txtUserName;

    private static final String TAG1 = "TAG";
    String[] addresses = new String[]{"support@treknation.ca"};
    int[] savedList = new int[13];
    private StepsAdapter stepsAdapter;
    boolean isStarted = false;
    SharedPreferences prefs;
    private View cardViewGetStarted;
    LinkedList<Item> steps = new LinkedList<Item>();
    private AppCompatImageView mIvsearch;
    private AppCompatEditText mEditTextSearch;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        itemList = new ArrayList<>();
        itemList = Utils.getInstance().getAllItems();

        prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        System.out.println("Before" + itemList);
        int current = 0;
        int prev = 0;
        int next;
        steps.clear();
        for (int i = 0; i < itemList.size(); i++) {
            steps.add(itemList.get(i));
            int idName = prefs.getInt("name" + itemList.get(i).getId(), 1000);
            System.out.println("IDname" + idName);
            if (itemList.get(i).getId() == idName) {
                current = i;
                itemList.get(i).isViewed = true;
                isStarted = true;
            }
        }

        itemRecView = findViewById(R.id.itemRecView);
        stepsRecView = findViewById(R.id.rc_status);
        txtTrekNation = findViewById(R.id.txtTrekNation);
        home_logo = findViewById(R.id.home_logo);
        txtSellAll = findViewById(R.id.txtSellAll);
        txtStartYourJourney = findViewById(R.id.txtStartYourJourney);
        txtUserName = findViewById(R.id.txtUserName);
        cardViewGetStarted = findViewById(R.id.cardView_get_started);
        mIvsearch = findViewById(R.id.iv_search);
        mEditTextSearch = findViewById(R.id.editTextSearch);
        final CardView mCvEdiText = findViewById(R.id.cvEdiText);
        final TextView mIvClose = findViewById(R.id.ivClose);


        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String name = preferences.getString(OnBoardingFragment.USERNAME, "");
        if (preferences.getBoolean(IS_FIRST_TIME, false)) {

            String stage = preferences.getString(USERSTAGE, "");
            if (stage.equals(getString(R.string.created_profile))) {
                completed(5);
            } else if (stage.equals(getString(R.string.received_ita))) {
                completed(6);
            } else if (stage.equals(getString(R.string.submitted_docs))) {
                completed(8);
            } else if (stage.equals(getString(R.string.landing_procedures))) {
                completed(10);
            }


            txtUserName.setText("Welcome, " + name + "!");
            HashMap<String, String> HashMap = new HashMap<String, String>();
            EventTracker.Companion.logEvent(EventTracker.Companion.getDashboard_onboarded(), HashMap);
        } else {
            txtUserName.setText("Welcome back, " + name + "!");
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_FIRST_TIME, false);
        editor.apply();


        //Todo dummy code of adapter


        adapter = new RecycleViewItemListAdapter(this, itemList);
        itemRecView.setAdapter(adapter);
        itemRecView.setLayoutManager(new LinearLayoutManager(this));

        if (isStarted) {
            stepsRecView.setVisibility(View.VISIBLE);
            cardViewGetStarted.setVisibility(View.GONE);
            checkStared();
        } else {
            cardViewGetStarted.setVisibility(View.VISIBLE);
            stepsRecView.setVisibility(View.GONE);


        }


//        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
//        String savedString = sharedPreferences.getString("string", "");
//        StringTokenizer st = new StringTokenizer(savedString, ",");

//        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString("task list", null);
//        Type type = new TypeToken<ArrayList<Integer>>() {
//        }.getType();
//        savedList = gson.fromJson(json, type);

//        StringTokenizer st = new StringTokenizer(savedString, ",");

//        for (int i = 0; i < 10; i++) {
//            savedList[i] = Integer.parseInt(st.nextToken());
//        }
//
//        if (savedString != null) {
//            for (int i = 0; i < savedList.length; i++) {
//                itemList.get(savedList[i]).isViewed = true;
//            }
//        }

//        for(int i=0; i<savedString.length(); i++){
//            savedList[i] = Integer.parseInt(st.nextToken());
//        }
//

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


        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("Action") != null) {
                Intent intent = new Intent(HomeActivity.this, ExpressEntryResultsActivity.class);
                startActivity(intent);
            }

        }

        txtAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> HashMap = new HashMap<String, String>();
                HashMap.put(EventTracker.Companion.getValue(), "About Us");
                EventTracker.Companion.logEvent(EventTracker.Companion.getSettings_menu(), HashMap);

                Intent intent = new Intent(HomeActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });

        txtFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String, String> HashMap = new HashMap<String, String>();
                HashMap.put(EventTracker.Companion.getValue(), "Feedback");
                EventTracker.Companion.logEvent(EventTracker.Companion.getSettings_menu(), HashMap);

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "support@treknation.ca", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "User Feedback");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        txtDisclaimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> HashMap = new HashMap<String, String>();
                HashMap.put(EventTracker.Companion.getValue(), "Disclaimer");
                EventTracker.Companion.logEvent(EventTracker.Companion.getSettings_menu(), HashMap);

                Intent intent = new Intent(HomeActivity.this, Disclaimer.class);
                startActivity(intent);
            }
        });

        txtPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> HashMap = new HashMap<String, String>();
                HashMap.put(EventTracker.Companion.getValue(), "Privacy Policy");
                EventTracker.Companion.logEvent(EventTracker.Companion.getSettings_menu(), HashMap);

                Intent intent = new Intent(HomeActivity.this, PrivacyPolicy.class);
                startActivity(intent);
            }
        });

        txtCicContactInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> HashMap = new HashMap<String, String>();
                HashMap.put(EventTracker.Companion.getValue(), "CicContact Information");
                EventTracker.Companion.logEvent(EventTracker.Companion.getSettings_menu(), HashMap);

                Intent intent = new Intent(HomeActivity.this, CicContact.class);
                startActivity(intent);
            }
        });

        mTxtCommonMistakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> HashMap = new HashMap<String, String>();
                HashMap.put(EventTracker.Companion.getValue(), "Common Mistakes");
                EventTracker.Companion.logEvent(EventTracker.Companion.getSettings_menu(), HashMap);

                Intent intent = new Intent(HomeActivity.this, CommonMistakeActivity.class);
                startActivity(intent);
            }
        });


        txtSellAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomNavigationView.setSelectedItemId(R.id.guide);
                includeMain.setVisibility(View.VISIBLE);
                includeDashboard.setVisibility(View.GONE);
                includeMore.setVisibility(View.GONE);

            }
        });

        txtStartYourJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomNavigationView.setSelectedItemId(R.id.guide);
                includeMain.setVisibility(View.VISIBLE);
                includeDashboard.setVisibility(View.GONE);
                includeMore.setVisibility(View.GONE);


            }
        });

        findViewById(R.id.cardView_ee_draw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> HashMap = new HashMap<String, String>();
                EventTracker.Companion.logEvent(EventTracker.Companion.getDashboard_EEDraws(), HashMap);

                Intent intent = new Intent(HomeActivity.this, ExpressEntryResultsActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.cardView_crs_calculator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> HashMap = new HashMap<String, String>();
                EventTracker.Companion.logEvent(EventTracker.Companion.getDashboard_CRSCalculator(), HashMap);

                Intent intent = new Intent(HomeActivity.this, CRSCalculatorActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.cardView_latest_blog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> HashMap = new HashMap<String, String>();
                EventTracker.Companion.logEvent(EventTracker.Companion.getDashboard_Blogs(), HashMap);

                Intent intent5 = new Intent(getApplicationContext(), WebsiteActivity.class);
                intent5.putExtra("Url 1", "https://treknation.ca/blogs/");
                startActivity(intent5);
            }
        });

        mIvsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCvEdiText.setVisibility(View.VISIBLE);
                mIvClose.setVisibility(View.VISIBLE);
                mIvsearch.setVisibility(View.INVISIBLE);
            }
        });
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditTextSearch.setText("");
                adapter.setFilter("");
                mIvClose.setVisibility(View.GONE);
                mIvsearch.setVisibility(View.VISIBLE);
                mCvEdiText.setVisibility(View.GONE);
            }
        });

        mEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.setFilter(editable.toString().toLowerCase());
            }
        });
    }

    private void completed(int lastStep) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        if (itemList.size() > lastStep) {
            for (int i = 0; i < itemList.size(); i++) {
                if (lastStep >= i) {
                    isStarted = true;
                    itemList.get(i).isViewed = true;
                    editor.putInt("name" + itemList.get(i).getId(), i);
                    editor.putInt("itemID", i + 1);
                    editor.putBoolean(itemList.get(i).getItemName(), true);
                    editor.apply();
                } else {
                    break;
                }
            }
        }
    }

    private void getSteps(ArrayList<Item> steps, int current) {

        if (steps.get(current) != null) {
            findViewById(R.id.cv_complted).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.txt_complted)).setText(steps.get(current).getItemNameWithoutSequencesNumber());
            findViewById(R.id.cv_complted).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bottomNavigationView.setSelectedItemId(R.id.guide);
                    includeMain.setVisibility(View.VISIBLE);
                    includeDashboard.setVisibility(View.GONE);
                    includeMore.setVisibility(View.GONE);

                    Intent intent = new Intent(HomeActivity.this, LongDesc.class);
                    intent.putExtra(ITEM_ID_KEY, steps.get(current).getId());
                    intent.putExtra("Position", current);
                    startActivityForResult(intent, 1);
                }
            });

        }

        if (current + 1 < steps.size()) {
            findViewById(R.id.cv_inProgress).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.txt_inProgress)).setText(steps.get(current + 1).getItemNameWithoutSequencesNumber());
            findViewById(R.id.cv_inProgress).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bottomNavigationView.setSelectedItemId(R.id.guide);
                    includeMain.setVisibility(View.VISIBLE);
                    includeDashboard.setVisibility(View.GONE);
                    includeMore.setVisibility(View.GONE);

                    Intent intent = new Intent(HomeActivity.this, LongDesc.class);
                    intent.putExtra(ITEM_ID_KEY, steps.get(current + 1).getId());
                    intent.putExtra("Position", current + 1);
                    startActivityForResult(intent, 1);
                }
            });

        } else {
            findViewById(R.id.cv_inProgress).setVisibility(View.GONE);

        }

        if (current + 2 < steps.size()) {
            findViewById(R.id.cv_Next).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.txt_Next)).setText(steps.get(current + 2).getItemNameWithoutSequencesNumber());
            findViewById(R.id.cv_Next).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bottomNavigationView.setSelectedItemId(R.id.guide);
                    includeMain.setVisibility(View.VISIBLE);
                    includeDashboard.setVisibility(View.GONE);
                    includeMore.setVisibility(View.GONE);

                    Intent intent = new Intent(HomeActivity.this, LongDesc.class);
                    intent.putExtra(ITEM_ID_KEY, steps.get(current + 2).getId());
                    intent.putExtra("Position", current + 2);
                    startActivityForResult(intent, 1);
                }
            });


        } else {
            findViewById(R.id.cv_Next).setVisibility(View.GONE);

        }

    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottomNavView);
        txtAboutUs = findViewById(R.id.txtAboutUs);
        txtFeedback = findViewById(R.id.txtFeedback);
        txtDisclaimer = findViewById(R.id.txtDisclaimer);
        txtPrivacyPolicy = findViewById(R.id.txtPrivacyPolicy);
        txtCicContactInformation = findViewById(R.id.txtCicContactInformation);
        mTxtCommonMistakes = findViewById(R.id.txtCommonMistakes);


        includeMain = findViewById(R.id.lyt_main);
        includeMore = findViewById(R.id.lyt_more);
        includeDashboard = findViewById(R.id.lyt_dashboard);
       /* mainLyt = includeMain.findViewById(R.id.main_activity);
        moreLyt = includeMore.findViewById(R.id.more_activity);*/

        initBottomNavView();
    }

    private void checkStared() {
        int current = 0;
        for (int i = 0; i < itemList.size(); i++) {

            int idName = prefs.getInt("name" + itemList.get(i).getId(), 1000);
            System.out.println("IDname" + idName);
            if (itemList.get(i).getId() == idName) {
                current = i;
                itemList.get(i).isViewed = true;
                isStarted = true;
            }
        }


        if (isStarted) {
            stepsRecView.setVisibility(View.VISIBLE);
            cardViewGetStarted.setVisibility(View.GONE);
            getSteps(itemList, current);
            adapter.notifyDataSetChanged();


        } else {
            cardViewGetStarted.setVisibility(View.VISIBLE);
            stepsRecView.setVisibility(View.GONE);

        }


    }

    private void initBottomNavView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.guide:
                        includeMain.setVisibility(View.VISIBLE);
                        includeDashboard.setVisibility(View.GONE);
                        includeMore.setVisibility(View.GONE);
                        checkStared();
                        break;
                    case R.id.other:
                        includeMain.setVisibility(View.GONE);
                        includeDashboard.setVisibility(View.GONE);
                        includeMore.setVisibility(View.VISIBLE);
                        checkStared();
                        break;
                    case R.id.home:
                        includeMain.setVisibility(View.GONE);
                        includeMore.setVisibility(View.GONE);
                        includeDashboard.setVisibility(View.VISIBLE);
                        HashMap<String, String> HashMap = new HashMap<String, String>();
                        EventTracker.Companion.logEvent(EventTracker.Companion.getDashboard_tab(), HashMap);
                        checkStared();
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

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(itemList.get(position).getItemName(), true);
                editor.putInt("name" + itemList.get(position).getId(), position);

                editor.apply();

                adapter.notifyDataSetChanged();
            }
        }
    }
}