package ca.treknation.myapplicationclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MoreActivity extends AppCompatActivity {

    private TextView txtAboutUs, txtFeedback, txtDisclaimer, txtPrivacyPolicy, txtCicContactInformation;

    private static final String TAG = "TAG";
    private BottomNavigationView bottomNavView;
    String subject = "User Feedback";
    String[] addresses = new String[]{"support@treknation.ca"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        initViews();
        initBottomNavView();

        txtAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });

        txtFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "support@treknation.ca", null));
//                emailIntent.setType("message/rfc822");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        txtDisclaimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, Disclaimer.class);
                startActivity(intent);
            }
        });

        txtPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, PrivacyPolicy.class);
                startActivity(intent);
            }
        });

        txtCicContactInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, CicContact.class);
                startActivity(intent);
            }
        });


    }

    private void initViews() {
        bottomNavView = findViewById(R.id.bottomNavView);
        txtAboutUs = findViewById(R.id.txtAboutUs);
        txtFeedback = findViewById(R.id.txtFeedback);
        txtDisclaimer = findViewById(R.id.txtDisclaimer);
        txtPrivacyPolicy = findViewById(R.id.txtPrivacyPolicy);
        txtCicContactInformation = findViewById(R.id.txtCicContactInformation);
    }

    private void initBottomNavView() {
        Log.d(TAG, "initBottomNavView: started");
        bottomNavView.setSelectedItemId(R.id.other);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent(MoreActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                    case R.id.other:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}