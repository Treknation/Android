package ca.treknation.myapplicationclone;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Fsw extends AppCompatActivity {
    private ImageView btnBackArrow8;
    private TextView txtFswDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsw);
        intiViews();
        txtFswDesc.setMovementMethod(LinkMovementMethod.getInstance());
        btnBackArrow8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fsw.this.finish();
            }
        });
    }

    private void intiViews() {

        btnBackArrow8 = findViewById(R.id.btnBackArrow8);
        txtFswDesc = findViewById(R.id.txtFswDesc);
    }
}
