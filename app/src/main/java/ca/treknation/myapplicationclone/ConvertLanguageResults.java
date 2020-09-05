package ca.treknation.myapplicationclone;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConvertLanguageResults extends AppCompatActivity {

    private ImageView btnBackArrow3;
    private TextView textView3;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_lanugage_results);
        initViews();
        textView3.setMovementMethod(LinkMovementMethod.getInstance());

//        String text = App.getContext().getString(R.string.convert_language_results);
//        Spanned styled_overview_long_description = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
//
//        textView3.setText(styled_overview_long_description);
//                "For each test, there is a conversion table from the test mark per ability (speaking, reading, listening, writing) to the Canadian Level Benchmark (CLB). CLBs are the norm for assessing language ability by IRCC. You can check your conversion through this link.";
//
//        SpannableString ss = new SpannableString((text));
//
//        ClickableSpan clickableSpan1 = new ClickableSpan() {
//            @Override
//            public void onClick(@NonNull View widget) {
//
//            }
//        };
//
//        ss.setSpan(clickableSpan1, 256, 259, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        textView3.setText(ss);
//        textView3.setMovementMethod(LinkMovementMethod.getInstance());

        btnBackArrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertLanguageResults.this.finish();
            }
        });
    }

    private void initViews() {
        btnBackArrow3 = findViewById(R.id.btnBackArrow3);
        textView3 = findViewById(R.id.textView3);
    }
}