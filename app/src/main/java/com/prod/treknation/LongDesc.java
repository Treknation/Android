package com.prod.treknation;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static com.prod.treknation.MainActivity.SHARED_PREFS;

public class LongDesc extends AppCompatActivity {

    public static final String ITEM_ID_KEY = "itemID";
    private static final String TAG = "TAG";
    private static final String TAG2 = "LongDesc started";
    ArrayList<Item> mItemList;
    //    ArrayList<Integer> list;
    int[] list = new int[10];
    private static final String PREFS_NAME = "preferenceName";
    public static final String USERSTAGE = "userstage";

    private TextView txtItemName, txtLongDesc, txtCRSCalculator;
    private Button btnMarkComplete, btnCosts, btnSubmitEE, btnTestResult, btnTestCost, btnCEC, btnFSW, btnFST;
    private ImageView btnCostsArrow, btnSubmitEEArrow, btnTestResultArrow, btnTestCostArrow, btnCECArrow, btnFSWArrow, btnFSTArrow;
    private ImageView btnBackArrow;
    private Context lContext;
    ConstraintLayout constraintLayout;
    ConstraintSet constraintSet;


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


        Intent intent = getIntent();
        if (null != intent) {
            //check to see if final required or not; made itemID final since it was giving error while clicking on markasComplete putExtra
            final int itemID = intent.getIntExtra(ITEM_ID_KEY, -1);
            final int position = intent.getIntExtra("Position", -1);

            SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            int idName = prefs.getInt("name" + itemID, 1000);

            if (idName == itemID) {
                btnMarkComplete.setText("Completed");
            }

/*
            SharedPreferences pref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            String stage = pref.getString(USERSTAGE,"");
            if (stage.equals(getString(R.string.created_profile))) {
                completed(5,position);
            } else if (stage.equals(getString(R.string.received_ita))) {
                completed(6,position);
            } else if (stage.equals(getString(R.string.submitted_docs))) {
                completed(8,position);
            } else if (stage.equals(getString(R.string.landing_procedures))) {
                completed(10,position);
            }
*/

            btnMarkComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(btnMarkComplete.getText()!="Completed"){
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        int next = prefs.getInt("itemID", 0);
                        if (next  == itemID) {
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("Item ID", itemID);
                            resultIntent.putExtra("Position", position);
                            setResult(RESULT_OK, resultIntent);
                            btnMarkComplete.setText("Completed");

                            HashMap<String, String> HashMap = new HashMap<String, String>();
                            if(Utils.getInstance().getAllItems().size()>itemID){
                                String nextName = Utils.getInstance().getAllItems().get(itemID).getItemName();
                                HashMap.put(EventTracker.Companion.getValue(), "yes");
                                EventTracker.Companion.logEvent("guide_"+nextName, HashMap);
                            }



                            Log.d(TAG, "onClick: Long Desc activity btnMarkComplete purav");
                            // Adding the item to shared preferences
//                    SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    Gson gson = new Gson();
//                    String json = gson.toJson(position);
//                    editor.putString("task list", json);
//                    editor.apply();
//                    list.add(itemID);
//                    System.out.println("Purav");
//                    for (int i = 0; i < list.length; i++) {
//                        if (list =id= null) {
//                            list = new int[]{itemID};
//                        } else {
//                            list.add(itemID);
//                        }
//                        str.append(list[i]).append(",");
//                    }
//                    Solution #1
                            editor.putInt("itemID", itemID+1);
                            editor.putInt("name" + itemID, itemID);
                            editor.apply();
                            // Solution #1 End

                            // Solution 2

//                    public static boolean setPreference(Context context, String key, String value) {
//                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = settings.edit();
//                        editor.putString(key, value);
//                        return editor.commit(); }
//                    StringBuilder str = new StringBuilder();
//                    for (int i = 0; i < list.length; i++) {
//                        str.append(list[i]).append(",");
//                    }
//                    prefs.edit().putString("string", str.toString());
//                    prefs.edit().commit();
//                    sharedPreferences.edit().putString("string", str.toString());
//                    System.out.println(list);
//                    System.out.println("Purav");
//                    String json = gson.toJson(mItemList);
//                    editor.putString("task list", json);
//                    editor.apply();
                            // end
                            finish();
                        } else {
                            if(Utils.getInstance().getAllItems().size()>next){
                                String nextName = Utils.getInstance().getAllItems().get(next).getItemName();
                                Toast.makeText(LongDesc.this, "Please complete step \"" + getString(R.string.label_com) + nextName + getString(R.string.label_com) + "\" first", Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                }
            });

            constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(btnMarkComplete.getId(), ConstraintSet.TOP, txtCRSCalculator.getId(), ConstraintSet.BOTTOM, 25);
            constraintSet.applyTo(constraintLayout);

            if (itemID == 5) {
                txtCRSCalculator.setVisibility(View.VISIBLE);
            }else{
                txtCRSCalculator.setVisibility(View.GONE);
            }
            if (itemID != -1) {
                Item incomingItem = Utils.getInstance().getItemById(itemID);
                if (null != incomingItem) {
                    setData(incomingItem,itemID);
                }
                if (itemID != 2) {
                    btnCosts.setVisibility(View.GONE);
                    btnSubmitEE.setVisibility(View.GONE);
                    btnCostsArrow.setVisibility(View.GONE);
                    btnSubmitEEArrow.setVisibility(View.GONE);
                } else {
                    btnCosts.setVisibility(View.VISIBLE);
                    btnSubmitEE.setVisibility(View.VISIBLE);
                    btnCostsArrow.setVisibility(View.VISIBLE);
                    btnSubmitEEArrow.setVisibility(View.VISIBLE);

                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(btnMarkComplete.getId(), ConstraintSet.TOP, btnSubmitEE.getId(), ConstraintSet.BOTTOM, 25);
                    constraintSet.applyTo(constraintLayout);

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
                if ((itemID != 3)) {
                    btnTestResult.setVisibility(View.GONE);
                    btnTestCost.setVisibility(View.GONE);
                    btnTestResultArrow.setVisibility(View.GONE);
                    btnTestCostArrow.setVisibility(View.GONE);
                } else {
                    btnTestResult.setVisibility(View.VISIBLE);
                    btnTestCost.setVisibility(View.VISIBLE);
                    btnTestResultArrow.setVisibility(View.VISIBLE);
                    btnTestCostArrow.setVisibility(View.VISIBLE);

                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(btnMarkComplete.getId(), ConstraintSet.TOP, btnTestCost.getId(), ConstraintSet.BOTTOM, 25);
                    constraintSet.applyTo(constraintLayout);

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

                if ((itemID != 4)) {
                    btnCEC.setVisibility(View.GONE);
                    btnFST.setVisibility(View.GONE);
                    btnFSW.setVisibility(View.GONE);
                    btnCECArrow.setVisibility(View.GONE);
                    btnFSTArrow.setVisibility(View.GONE);
                    btnFSWArrow.setVisibility(View.GONE);
                } else {
                    btnCEC.setVisibility(View.VISIBLE);
                    btnFST.setVisibility(View.VISIBLE);
                    btnFSW.setVisibility(View.VISIBLE);
                    btnCECArrow.setVisibility(View.VISIBLE);
                    btnFSTArrow.setVisibility(View.VISIBLE);
                    btnFSWArrow.setVisibility(View.VISIBLE);

                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(btnMarkComplete.getId(), ConstraintSet.TOP, btnFST.getId(), ConstraintSet.BOTTOM, 25);

                    constraintSet.applyTo(constraintLayout);

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

//                if ((itemID == 11)) {
//
//
//
//                } else {
//
//                    btnInCanada.setVisibility(View.VISIBLE);
//                    btnOutsideCanada.setVisibility(View.VISIBLE);
//                    btnInCanadaArrow.setVisibility(View.VISIBLE);
//                    btnOutsideCanadaArrow.setVisibility(View.VISIBLE);
//
//                    constraintSet = new ConstraintSet();
//                    constraintSet.clone(constraintLayout);
//                    constraintSet.connect(btnMarkComplete.getId(), ConstraintSet.TOP, btnOutsideCanada.getId(), ConstraintSet.BOTTOM, 25);
//                    constraintSet.applyTo(constraintLayout);
//
//
//                    btnInCanada.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent8 = new Intent(LongDesc.this, InCanada.class);
//                            startActivity(intent8);
//                        }
//                    });
//
//                    btnOutsideCanada.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent9 = new Intent(LongDesc.this, OutsideCanada.class);
//                            startActivity(intent9);
//                        }
//                    });
//
//                }

            }

        }

        makeLinks(new Pair("here", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LongDesc.this, CRSCalculatorActivity.class);
                startActivity(intent);
            }
        }),txtCRSCalculator);

    }

    private void makeLinks(Pair<String, View.OnClickListener> link, TextView textView) {
        SpannableString ss = new SpannableString(textView.getText());
        int sIndex = 1;
        ClickableSpan cs = new ClickableSpan() {

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(ContextCompat.getColor(LongDesc.this,R.color.com_facebook_messenger_blue));
                ds.setUnderlineText(true);
            }
            @Override
            public void onClick(@NonNull View view) {
                if (view instanceof TextView) {
                    TextView tv = (TextView)view;
                    Spannable sp = (Spannable)tv.getText();
                    Selection.setSelection(sp,0);
                }
                view.invalidate();
                link.second.onClick(view);
            }
        };
        sIndex = textView.getText().toString().indexOf(link.first, sIndex + 1);
        ss.setSpan(cs, sIndex, sIndex + link.first.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(ss,TextView.BufferType.SPANNABLE);
    }

    private void completed(int step, int itemPosition) {
        if (itemPosition <= step) {
            btnMarkComplete.setText("Completed");
        }
    }


    private void setData(Item item,int itemId) {
        txtItemName.setText(item.getItemName());
        //txtLongDesc.setText(item.getItemLongDesc());

        SharedPreferences pref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        if (itemId == 8) {
            if(pref.getString("isSingle","").equals(getString(R.string.individual))) {
                setTextViewHTML(txtLongDesc, Utils.getSingle_styled_checklist_long_desc());
            } else if(pref.getString("isSingle","").equals(getString(R.string.family))) {
                setTextViewHTML(txtLongDesc,Utils.getMarried_styled_checklist_long_desc());
            } else {
                setTextViewHTML(txtLongDesc, item.getItemLongDesc());
            }
        } else if (itemId == 11) {

            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            boolean isFromCanada = sharedPreferences.getBoolean(OnBoardingFragment.IS_FROM_CANADA,false);

            if (isFromCanada) {
                String in_canada = App.getContext().getString(R.string.in_canada);
                Spanned styled_in_canada = Html.fromHtml(in_canada, Html.FROM_HTML_MODE_LEGACY);
                setTextViewHTML(txtLongDesc, styled_in_canada);
            } else {
                String outside_canada = App.getContext().getString(R.string.outside_canada);
                Spanned styled_outside_canada = Html.fromHtml(outside_canada, Html.FROM_HTML_MODE_LEGACY);
                setTextViewHTML(txtLongDesc, styled_outside_canada);
            }

        } else {
            setTextViewHTML(txtLongDesc, item.getItemLongDesc());
        }
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
        txtItemName = findViewById(R.id.txtItemName);
        txtLongDesc = findViewById(R.id.txtLongDesc);
        txtCRSCalculator = findViewById(R.id.txtCRSCalculator);
        btnMarkComplete = findViewById(R.id.btnMarkComplete);
        btnBackArrow = findViewById(R.id.btnBackArrow);
        btnSubmitEE = findViewById(R.id.btnSubmitEE);
        btnCosts = findViewById(R.id.btnCosts);
        btnTestResult = findViewById(R.id.btnTestResult);
        btnTestCost = findViewById(R.id.btnTestCost);
        btnCEC = findViewById(R.id.btnCEC);
        btnFSW = findViewById(R.id.btnFSW);
        btnFST = findViewById(R.id.btnFST);

        btnCostsArrow = findViewById(R.id.btnCostsArrow);
        btnSubmitEEArrow = findViewById(R.id.btnSubmitEEArrow);
        btnTestResultArrow = findViewById(R.id.btnTestResultArrow);
        btnTestCostArrow = findViewById(R.id.btnTestCostArrow);
        btnCECArrow = findViewById(R.id.btnCECArrow);
        btnFSWArrow = findViewById(R.id.btnFSWArrow);
        btnFSTArrow = findViewById(R.id.btnFSTArrow);

        constraintLayout = findViewById(R.id.constraintLayout);
    }
}