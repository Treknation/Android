package ca.treknation.myapplicationclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView itemRecView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemRecView = findViewById(R.id.itemRecView);

        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item(1, "What is Express Entry", "Express Entry is an electronic immigration selection system for selecting applicants for permanent residence under the 3 programs Federal Skilled Worker, Canadian Experience Class, and Federal Skilled Trades. You can also apply through express entry if you have received a nomination from a province or territory. This nomination is also called PNP which stands for Provincial Nominee Program.\n" +
                "Candidates can fill their online profile and the eligibility is electronically determined for the programs mentioned above. Using this information, the candidates are given a Comprehensive Ranking System (CRS) score. They are then placed in the Express Entry pool and ranked relative to each other based on their CRS scores. Top ranking candidates within the pool are invited to apply for permanent residence after each pool.\n"));
        items.add(new Item(2, "Language Tests", "In this step, you will be required to take a language test in English and/or French and score a number of points to be eligible for Express Entry programs. Express Entry uses CLB(Canadian Language Benchmark) as a method of checking your language level based on your test results. \n" +
                "For all the three Express Entry programs, you need to take English and/or French test(s) and score a minimum score to be eligible.\n"));
        items.add(new Item(3, "NOC board", "NOC stands for National Occupation Classification. It helps to determine your job code which you will need while creating your EE profile. There are 5 types of NOC groups: NOC 0, A, B, C and D"));
        items.add(new Item(4, "Get ECA", "An Educational Credential Assessment (ECA) is used to verify that your foreign degree, diploma, or certificate (or other proof of your credential) is valid and equal to a Canadian one. You would need an ECA only if you have a non-Canadian degree and must be issued for Immigration purposes."));
        items.add(new Item(5, "Check Eligibility", "In order to qualify for Express Entry for one of the three programs, you must meet the minimum requirements."));
        items.add(new Item(6, "CRS scores", "CRS Score is the score used by IRCC to rank candidates in the Express Entry pool. You can check your CRS score through this link."));
        items.add(new Item(7, "Enter Express Entry Pool ", "In this step, you will now create your IRCC secure account and get into the pool of applicants for your EE program.\n" +
                "Please make sure you have completed the below steps before proceeding to create your profile\n" +
                "ECA from step 2\n" +
                "Language Test details from Step 3\n" +
                "Personal preference code from the Eligibility form in step 4\n"));
        items.add(new Item(8, "Receiving ITA", "Once you have created your online profile you would need to wait for an ITA(Invitation to Apply). You will get an invite if your CRS score is greater than or equal to the cut-off of the draw."));
        items.add(new Item(9, "Document Checklist", "Once you get the ITA and fill out the post-ITA application forms, a personalized document checklist will be generated for your profile. You will have 60 days to gather all documents and upload the scanned copies."));
        items.add(new Item(10, "E-APR Fees", "Once you fill out the online form and upload the documents in the last step, you will be shown fees you need to pay for your e-APR (Electronic Application for Permanent Residence). The fees are of the following types\n" +
                "·       Processing fee for you and your family members\n" +
                "·       Right of Permanent residence fee\n" +
                "·       Biometrics fee"));
        items.add(new Item(11, "AOR to PPR", "After submitting your e-APR, you will receive an Acknowledgement of Receipt (AOR). This is the AOR date and is the date from when your 6 months intended processing time begins. Once IRCC has reviewed your biometrics, background checks, and medical examinations evaluations you will get a golden email for Passport Request (PPR)."));
        items.add(new Item(12, "Preparing to Land", "Congratulations!! now that you have received your COPR you are just one step away from getting your PR card. The next steps will depend on whether you are inside or outside of Canada."));
        itemRecViewAdapter adapter = new itemRecViewAdapter();
        adapter.setItems(items);
        itemRecView.setAdapter(adapter);
        itemRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}