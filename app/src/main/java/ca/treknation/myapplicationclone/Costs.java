package ca.treknation.myapplicationclone;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Costs extends AppCompatActivity {


    private TextView getYourEca, costsTitle, costsDesc, row11, row12, row13, row21, row22, row23, row31, row32, row33, row41, row42, row43, row51, row52,
            row53, row61, row62, row63;
    private ImageView btnBackArrow1;
    private TableLayout table;
    private TableRow row1, row2, row3, row4, row5, row6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costs);

        initViews();

        btnBackArrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Costs.this.finish();
            }
        });
    }

    private void initViews() {
        getYourEca = findViewById(R.id.getYourEca);
        costsTitle = findViewById(R.id.costsTitle);
        costsDesc = findViewById(R.id.costsDesc);
        row11 = findViewById(R.id.row11);
        row12 = findViewById(R.id.row12);
        row13 = findViewById(R.id.row13);
        row21 = findViewById(R.id.row21);
        row22 = findViewById(R.id.row22);
        row23 = findViewById(R.id.row23);
        row31 = findViewById(R.id.row31);
        row32 = findViewById(R.id.row32);
        row33 = findViewById(R.id.row33);
        row41 = findViewById(R.id.row41);
        row42 = findViewById(R.id.row42);
        row43 = findViewById(R.id.row43);
        row51 = findViewById(R.id.row51);
        row52 = findViewById(R.id.row52);
        row53 = findViewById(R.id.row53);
        row61 = findViewById(R.id.row61);
        row62 = findViewById(R.id.row62);
        row63 = findViewById(R.id.row63);
        btnBackArrow1 = findViewById(R.id.btnBackArrow1);
        table = findViewById(R.id.table);
        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);
        row4 = findViewById(R.id.row4);
        row5 = findViewById(R.id.row5);
        row6 = findViewById(R.id.row6);
    }
}