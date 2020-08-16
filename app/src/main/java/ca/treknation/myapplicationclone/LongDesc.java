package ca.treknation.myapplicationclone;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class LongDesc extends AppCompatActivity {

    public static final String ITEM_ID_KEY = "itemID";

    private TextView txtItemName, txtLongDesc;
    private Button btnMarkComplete, btnCosts, btnSubmitEE, btnTestResult, btnTestCost, btnCEC, btnFSW, btnFST;
    private ImageView btnBackArrow;
    private ScrollView scrollerID;
    private Context lContext;

//    public LongDesc(Context lContext) {
//        this.lContext = lContext;
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_desc);
        initViews();
        txtLongDesc.setMovementMethod(LinkMovementMethod.getInstance());

        btnMarkComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LongDesc.this, "This is sample text", Toast.LENGTH_SHORT).show();
            }
        });

        btnBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LongDesc.this.finish();
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
            int itemID = intent.getIntExtra(ITEM_ID_KEY, -1);
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
        txtLongDesc.setText(item.getItemLongDesc());
    }


//    private void setData(Item item) {
//        txtLongDesc.setText(item.getItemLongDesc());
//    }


    private void initViews() {
        txtItemName = findViewById(R.id.txtItemName);
        txtLongDesc = findViewById(R.id.txtLongDesc);
        btnMarkComplete = findViewById(R.id.btnMarkComplete);
        btnBackArrow = findViewById(R.id.btnBackArrow);
        btnSubmitEE = findViewById(R.id.btnSubmitEE);
        btnCosts = findViewById(R.id.btnCosts);
        btnTestResult = findViewById(R.id.btnTestResult);
        btnTestCost = findViewById(R.id.btnTestCost);
        scrollerID = findViewById(R.id.scrollerID);
        btnCEC = findViewById(R.id.btnCEC);
        btnFSW = findViewById(R.id.btnFSW);
        btnFST = findViewById(R.id.btnFST);
    }
}