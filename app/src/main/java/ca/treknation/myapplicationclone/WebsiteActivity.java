package ca.treknation.myapplicationclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        webView = findViewById(R.id.webView);
        Intent intent = getIntent();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(intent.getStringExtra("Url 1"));

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        getSupportActionBar().show();
    }
}