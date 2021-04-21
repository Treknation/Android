package com.prod.treknation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE);
        final boolean isIntroDone = prefs.getBoolean(OnBoardingFragment.IS_INTRO_DONE, false);


        HandlerCompat.postDelayed(new Handler(), new Runnable() {
            @Override
            public void run() {
                launchSplash(isIntroDone);
            }
        }, "0", SPLASH_TIME);
    }

    private void launchSplash(boolean isIntroDone) {
        if (isIntroDone) {
            Intent intent = new Intent(this, HomeActivity.class);
            if (getIntent().getExtras() != null) {
                if(getIntent().getExtras().getString("Action")!=null){
                    Log.d("Notification", getIntent().getExtras().toString());
                    intent.putExtra("Action", "ExpressEntryResultsActivity");
                }

            }
            startActivity(intent);

        } else {
            startActivity(new Intent(this, OnBoardingActivity.class));

        }
    }
}