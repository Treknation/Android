package com.prod.treknation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConvertLanguageResults extends AppCompatActivity {
    private ImageView btnBackArrow8;
    private TextView txtConvertDesc;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_lanugage_results);
        initViews();
        txtConvertDesc.setMovementMethod(LinkMovementMethod.getInstance());
        btnBackArrow8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertLanguageResults.this.finish();
            }
        });

        String convert_language_results_desc = App.getContext().getString(R.string.convert_language_results);
        Spanned styled_convert_language_results_desc = Html.fromHtml(convert_language_results_desc, Html.FROM_HTML_MODE_LEGACY);
        setTextViewHTML(txtConvertDesc, styled_convert_language_results_desc);

    }

    protected void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span) {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {
                //span.getURL(); -> to get the clicked URL
//                Toast.makeText(getApplicationContext(), "Link Clicked "+ span.getURL(), Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent(getApplicationContext(), WebsiteActivity.class);
                intent5.putExtra("Url 1", span.getURL());
                startActivity(intent5);
            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }

    protected void setTextViewHTML(TextView text, Spanned html) {
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(html);
        URLSpan[] urls = strBuilder.getSpans(0, html.length(), URLSpan.class);
        for (URLSpan span : urls) {
            makeLinkClickable(strBuilder, span);
        }
        text.setText(strBuilder);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initViews() {

        btnBackArrow8 = findViewById(R.id.btnBackArrow8);
        txtConvertDesc = findViewById(R.id.txtConvertDesc);
    }
}
