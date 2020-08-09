package ca.treknation.myapplicationclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LongDesc extends AppCompatActivity {

    public static final String ITEM_ID_KEY = "itemID";

    private TextView txtItemName, txtLongDesc;
    private Button btnMarkComplete;
    private ImageView btnBackArrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_desc);
        initViews();

//        Item item = new Item(1, "Overview", "Express Entry is an electronic immigration selection system for selecting applicants for permanent residence under the 3 programs Federal Skilled Worker, Canadian Experience Class, and Federal Skilled Trades. You can also apply through express entry if you have received a nomination from a province or territory. This nomination is also called PNP which stands for Provincial Nominee Program.\n" +
////                "Candidates can fill their online profile and the eligibility is electronically determined for the programs mentioned above. Using this information, the candidates are given a Comprehensive Ranking System (CRS) score. They are then placed in the Express Entry pool and ranked relative to each other based on their CRS scores. Top ranking candidates within the pool are invited to apply for permanent residence after each pool.\n", true, "This is long Desc");
////
////        setData(item);


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


        //TODO: Get the data from recylcer view in here

        Intent intent = getIntent();
        if (null != intent) {
            int itemID = intent.getIntExtra(ITEM_ID_KEY, -1);
            if (itemID != -1) {
                Item incomingItem = Utils.getInstance().getItemById(itemID);
                if (null != incomingItem) {
                    setData(incomingItem);
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
    }
}