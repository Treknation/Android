package ca.treknation.myapplicationclone;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Utils {

    private static Utils instance;
    private static ArrayList<Item> allItems;

    //    Short Description
    String overview_short_desc = App.getContext().getString(R.string.overview_short_description);
    String noc_short_desc = App.getContext().getString(R.string.noc_short_desc);
    String eca_short_desc = App.getContext().getString(R.string.eca_short_desc);
    String language_short_desc = App.getContext().getString(R.string.language_short_desc);
    String eligibility_short_desc = App.getContext().getString(R.string.eligibility_short_desc);
    String pool_short_desc = App.getContext().getString(R.string.pool_short_desc);
    String ita_short_desc = App.getContext().getString(R.string.ita_short_desc);
    String checklist_short_desc = App.getContext().getString(R.string.checklist_short_desc);
    String fees_short_desc = App.getContext().getString(R.string.fees_short_desc);
    String ppr_short_desc = App.getContext().getString(R.string.ppr_short_desc);
    String landing_short_desc = App.getContext().getString(R.string.landing_short_desc);


    //    Long Description
    String overview_long_description = App.getContext().getString(R.string.overview_long_description);
    Spanned styled_overview_long_description = Html.fromHtml(overview_long_description, Html.FROM_HTML_MODE_LEGACY);

    String noc_long_description = App.getContext().getString(R.string.noc_long_description);
    Spanned styled_noc_long_description = Html.fromHtml(noc_long_description, Html.FROM_HTML_MODE_LEGACY);

    String eca_long_description = App.getContext().getString(R.string.eca_long_description);
    Spanned styled_eca_long_description = Html.fromHtml(eca_long_description, Html.FROM_HTML_MODE_LEGACY);

    String language_long_desc = App.getContext().getString(R.string.language_long_desc);
    Spanned styled_language_long_desc = Html.fromHtml(language_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String eligibility_long_desc = App.getContext().getString(R.string.eligibility_long_desc);
    Spanned styled_eligibility_long_desc = Html.fromHtml(eligibility_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String pool_long_desc = App.getContext().getString(R.string.pool_long_desc);
    Spanned styled_pool_long_desc = Html.fromHtml(pool_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String ita_long_desc = App.getContext().getString(R.string.ita_long_desc);
    Spanned styled_ita_long_desc = Html.fromHtml(ita_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String checklist_long_desc = App.getContext().getString(R.string.checklist_long_desc);
    Spanned styled_checklist_long_desc = Html.fromHtml(checklist_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String fees_long_desc = App.getContext().getString(R.string.fees_long_desc);
    Spanned styled_fees_long_desc = Html.fromHtml(fees_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String ppr_long_desc = App.getContext().getString(R.string.ppr_long_desc);
    Spanned styled_ppr_long_desc = Html.fromHtml(ppr_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String landing_long_desc = App.getContext().getString(R.string.landing_long_desc);
    Spanned styled_landing_long_desc = Html.fromHtml(landing_long_desc, Html.FROM_HTML_MODE_LEGACY);


    private Utils() {
        if (null == allItems) {
            allItems = new ArrayList<>();
            initData();
        }
    }

    private void initData() {
        //TODO: add initial data
        allItems.add(new Item(1, "Overview", overview_short_desc, false, styled_overview_long_description));
        allItems.add(new Item(2, "Find your NOC", noc_short_desc, false, styled_noc_long_description));
        allItems.add(new Item(3, "Get your ECA", eca_short_desc, false, styled_eca_long_description));
        allItems.add(new Item(4, "Language Tests", language_short_desc, false, styled_language_long_desc));
        allItems.add(new Item(5, "Check Eligibility", eligibility_short_desc, false, styled_eligibility_long_desc));
        allItems.add(new Item(6, "Enter Express Entry Pool", pool_short_desc, false, styled_pool_long_desc));
        allItems.add(new Item(7, "Receiving ITA", ita_short_desc, false, styled_ita_long_desc));
        allItems.add(new Item(8, "Document Checklist", checklist_short_desc, false, styled_checklist_long_desc));
        allItems.add(new Item(9, "E-APR Fees", fees_short_desc, false, styled_fees_long_desc));
        allItems.add(new Item(10, "AOR to PPR", ppr_short_desc, false, styled_ppr_long_desc));
        allItems.add(new Item(11, "Prepare for Landing", landing_short_desc, false, styled_landing_long_desc));
    }

    public static Utils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Item> getAllItems() {
        return allItems;
    }

    public Item getItemById(int id) {
        for (Item b : allItems) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }
}
