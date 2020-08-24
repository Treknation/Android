package ca.treknation.myapplicationclone;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LongDesc extends AppCompatActivity {

    public static final String ITEM_ID_KEY = "itemID";
    private static final String TAG = "TAG";

    private TextView txtItemName, txtLongDesc;
    private Button btnMarkComplete, btnCosts, btnSubmitEE, btnTestResult, btnTestCost, btnCEC, btnFSW, btnFST;
    private ImageView btnBackArrow;
    private Context lContext;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_desc);
        initViews();


        btnBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LongDesc.this, "This is test", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        if (null != intent) {
            //check to see if final required or not; made itemID final since it was giving error while clicking on markasComplete putExtra
            final int itemID = intent.getIntExtra(ITEM_ID_KEY, -1);
            final int position = intent.getIntExtra("Position", -1);
            btnMarkComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("Item ID", itemID);
                    resultIntent.putExtra("Position", position);
                    setResult(RESULT_OK, resultIntent);
                    Log.d(TAG, "onClick: started");
                    finish();
                }
            });
            if (itemID != -1) {
                Item incomingItem = Utils.getInstance().getItemById(itemID);
                if (null != incomingItem) {
                    setData(incomingItem);
                }
                if (itemID != 3) {
                    btnCosts.setVisibility(View.GONE);
                    btnSubmitEE.setVisibility(View.GONE);
                } else {
                    btnCosts.setVisibility(View.VISIBLE);
                    btnSubmitEE.setVisibility(View.VISIBLE);

                    btnCosts.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1 = new Intent(LongDesc.this, Costs.class);
                            startActivity(intent1);
                        }
                    });

                    btnSubmitEE.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent2 = new Intent(LongDesc.this, Submit_ee.class);
                            startActivity(intent2);
                        }
                    });
                }
                if ((itemID != 4)) {
                    btnTestResult.setVisibility(View.GONE);
                    btnTestCost.setVisibility(View.GONE);
                } else {
                    btnTestResult.setVisibility(View.VISIBLE);
                    btnTestCost.setVisibility(View.VISIBLE);

                    btnTestResult.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent3 = new Intent(LongDesc.this, ConvertLanguageResults.class);
                            startActivity(intent3);
                        }
                    });

                    btnTestCost.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent4 = new Intent(LongDesc.this, LanguageCosts.class);
                            startActivity(intent4);
                        }
                    });
                }

                if ((itemID != 5)) {
                    btnCEC.setVisibility(View.GONE);
                    btnFST.setVisibility(View.GONE);
                    btnFSW.setVisibility(View.GONE);
                } else {
                    btnCEC.setVisibility(View.VISIBLE);
                    btnFST.setVisibility(View.VISIBLE);
                    btnFSW.setVisibility(View.VISIBLE);

                    btnCEC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent5 = new Intent(LongDesc.this, Cec.class);
                            startActivity(intent5);
                        }
                    });

                    btnFST.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent6 = new Intent(LongDesc.this, Fst.class);
                            startActivity(intent6);
                        }
                    });

                    btnFSW.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent7 = new Intent(LongDesc.this, Fsw.class);
                            startActivity(intent7);
                        }
                    });

                }

            }

        }


    }


    private void setData(Item item) {
        txtItemName.setText(item.getItemName());
        //txtLongDesc.setText(item.getItemLongDesc());

        setTextViewHTML(txtLongDesc,item.getItemLongDesc());
    }

    protected void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span)
    {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {
                //span.getURL(); -> to get the clicked URL
                Toast.makeText(getApplicationContext(), "Link Clicked "+ span.getURL(), Toast.LENGTH_SHORT).show();
            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }

    protected void setTextViewHTML(TextView text, Spanned html)
    {
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(html);
        URLSpan[] urls = strBuilder.getSpans(0, html.length(), URLSpan.class);
        for(URLSpan span : urls) {
            makeLinkClickable(strBuilder, span);
        }
        text.setText(strBuilder);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }

//    protected void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span)
//    {
////        int start = strBuilder.getSpanStart(span);
////        int end = strBuilder.getSpanEnd(span);
////        int flags = strBuilder.getSpanFlags(span);
//        ClickableSpan clickable = new ClickableSpan() {
//            public void onClick(View view) {
//                // Do something with span.getURL() to handle the link click...
//                Intent intent = new Intent(LongDesc.this, WebsiteActivity.class);
//                startActivity(intent);
//            }
//        };
//        strBuilder.setSpan(clickable, 1465, 1468, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        strBuilder.removeSpan(span);
//    }
//
//    protected void setTextViewHTML(TextView txtLongDesc, String html)
//    {
//        CharSequence sequence = Html.fromHtml(getResources().getString(R.string.overview_long_description));
//        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
//        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
//        for(URLSpan span : urls) {
//            makeLinkClickable(strBuilder, span);
//        }
//        txtLongDesc.setText(strBuilder);
//        txtLongDesc.setMovementMethod(LinkMovementMethod.getInstance());
//    }

//    SpannableStringBuilder ss = new SpannableStringBuilder(getResources().getString(R.string.overview_long_description));
//    ClickableSpan clickableSpan1 = new ClickableSpan() {
//        @Override
//        public void onClick(@NonNull View widget) {
//            Toast.makeText(LongDesc.this, "This is test", Toast.LENGTH_SHORT).show();
//        }
//    };
//
//    ss.Factory


    private void initViews() {
        txtItemName = findViewById(R.id.txtItemName);
        txtLongDesc = findViewById(R.id.txtLongDesc);
        btnMarkComplete = findViewById(R.id.btnMarkComplete);
        btnBackArrow = findViewById(R.id.btnBackArrow);
        btnSubmitEE = findViewById(R.id.btnSubmitEE);
        btnCosts = findViewById(R.id.btnCosts);
        btnTestResult = findViewById(R.id.btnTestResult);
        btnTestCost = findViewById(R.id.btnTestCost);
        btnCEC = findViewById(R.id.btnCEC);
        btnFSW = findViewById(R.id.btnFSW);
        btnFST = findViewById(R.id.btnFST);
    }
}