package com.prod.treknation;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Utils {

    private static Utils instance;
    private static ArrayList<Item> allItems;
    private static ArrayList<Item> commonMistakeItems;

    //    Short Description
    String overview_short_desc = App.getContext().getString(R.string.overview_short_description);
    String noc_short_desc = App.getContext().getString(R.string.noc_short_desc);
    String eca_short_desc = App.getContext().getString(R.string.eca_short_desc);
    String language_short_desc = App.getContext().getString(R.string.language_short_desc);
    String eligibility_short_desc = App.getContext().getString(R.string.eligibility_short_desc);
    String crs_short_desc = App.getContext().getString(R.string.crs_short_desc);
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

    //enter crs score long desc

    String crs_long_desc = App.getContext().getString(R.string.crs_long_desc);
    Spanned styled_crs_long_desc = Html.fromHtml(crs_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String pool_long_desc = App.getContext().getString(R.string.pool_long_desc);
    Spanned styled_pool_long_desc = Html.fromHtml(pool_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String ita_long_desc = App.getContext().getString(R.string.ita_long_desc);
    Spanned styled_ita_long_desc = Html.fromHtml(ita_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String checklist_long_desc = App.getContext().getString(R.string.checklist_long_desc);
    Spanned styled_checklist_long_desc = Html.fromHtml(checklist_long_desc, Html.FROM_HTML_MODE_LEGACY);

    private static String single_checklist_long_desc = App.getContext().getString(R.string.single_checklist_long_desc);
    private static Spanned single_styled_checklist_long_desc = Html.fromHtml(single_checklist_long_desc, Html.FROM_HTML_MODE_LEGACY);

    private static String married_checklist_long_desc = App.getContext().getString(R.string.married_checklist_long_desc);
    private static Spanned married_styled_checklist_long_desc = Html.fromHtml(married_checklist_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String fees_long_desc = App.getContext().getString(R.string.fees_long_desc);
    Spanned styled_fees_long_desc = Html.fromHtml(fees_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String ppr_long_desc = App.getContext().getString(R.string.ppr_long_desc);
    Spanned styled_ppr_long_desc = Html.fromHtml(ppr_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String landing_long_desc = App.getContext().getString(R.string.landing_long_desc);
    Spanned styled_landing_long_desc = Html.fromHtml(landing_long_desc, Html.FROM_HTML_MODE_LEGACY);

    String passport_scan_missing_desc = App.getContext().getString(R.string.passport_scan_missing_desc);
    Spanned spanned_styled_overview_long_description = Html.fromHtml(passport_scan_missing_desc, Html.FROM_HTML_MODE_LEGACY);

    String work_exp_desc_and_duties_desc = App.getContext().getString(R.string.work_exp_desc_and_duties_desc);
    Spanned spanned_work_exp_desc_and_duties_desc = Html.fromHtml(work_exp_desc_and_duties_desc, Html.FROM_HTML_MODE_LEGACY);

    String not_uploading_eca_desc = App.getContext().getString(R.string.not_uploading_eca_desc);
    Spanned spanned_not_uploading_eca_desc = Html.fromHtml(not_uploading_eca_desc, Html.FROM_HTML_MODE_LEGACY);

    String pcc_missing_desc = App.getContext().getString(R.string.pcc_missing_desc);
    Spanned spanned_pcc_missing_desc = Html.fromHtml(pcc_missing_desc, Html.FROM_HTML_MODE_LEGACY);

    String pcc_not_in_color_desc = App.getContext().getString(R.string.pcc_not_in_color_desc);
    Spanned spanned_pcc_not_in_color_desc = Html.fromHtml(pcc_not_in_color_desc, Html.FROM_HTML_MODE_LEGACY);

    String a_few_words_explicitly_written_desc = App.getContext().getString(R.string.a_few_words_explicitly_written_desc);
    Spanned spanned_a_few_words_explicitly_written_desc = Html.fromHtml(a_few_words_explicitly_written_desc, Html.FROM_HTML_MODE_LEGACY);

    String not_upload_english_french_translation_desc = App.getContext().getString(R.string.not_upload_english_french_translation_desc);
    Spanned spanned_not_upload_english_french_translation_desc = Html.fromHtml(not_upload_english_french_translation_desc, Html.FROM_HTML_MODE_LEGACY);

    String submitting_ban_statement_desc = App.getContext().getString(R.string.submitting_ban_statement_);
    Spanned spanned_submitting_ban_statement_desc = Html.fromHtml(submitting_ban_statement_desc, Html.FROM_HTML_MODE_LEGACY);

    String not_the_foreign_lang_desc = App.getContext().getString(R.string.not_the_foreign_lang_desc);
    Spanned spanned_not_the_foreign_lang_desc = Html.fromHtml(not_the_foreign_lang_desc, Html.FROM_HTML_MODE_LEGACY);

    String canadian_education_dec = App.getContext().getString(R.string.canadian_education_dec);
    Spanned spanned_canadian_education_dec = Html.fromHtml(canadian_education_dec, Html.FROM_HTML_MODE_LEGACY);

    String not_signing_application_forms_desc = App.getContext().getString(R.string.not_signing_application_forms_desc);
    Spanned spanned_not_signing_application_forms_desc = Html.fromHtml(not_signing_application_forms_desc, Html.FROM_HTML_MODE_LEGACY);

    String paying_the_fee_incorrectly_desc = App.getContext().getString(R.string.paying_the_fee_incorrectly_desc);
    Spanned spanned_paying_the_fee_incorrectly_desc = Html.fromHtml(paying_the_fee_incorrectly_desc, Html.FROM_HTML_MODE_LEGACY);

    String correct_size_photo_desc = App.getContext().getString(R.string.correct_size_photo_desc);
    Spanned spanned_correct_size_photo_desc = Html.fromHtml(correct_size_photo_desc, Html.FROM_HTML_MODE_LEGACY);

    String additional_forms_for_certain_countries_desc = App.getContext().getString(R.string.additional_forms_for_certain_countries_desc);
    Spanned spanned_additional_forms_for_certain_countries_desc = Html.fromHtml(additional_forms_for_certain_countries_desc, Html.FROM_HTML_MODE_LEGACY);

    String providing_info_decs = App.getContext().getString(R.string.providing_info_decs);
    Spanned spanned_providing_info_decs = Html.fromHtml(providing_info_decs, Html.FROM_HTML_MODE_LEGACY);

    String illegible_handwritten_desc = App.getContext().getString(R.string.illegible_handwritten_desc);
    Spanned spanned_illegible_handwritten_desc = Html.fromHtml(illegible_handwritten_desc, Html.FROM_HTML_MODE_LEGACY);


    private Utils() {
        if (null == allItems) {
            allItems = new ArrayList<>();
            commonMistakeItems = new ArrayList<>();
            initData();
        }
    }

    private void initData() {
        //TODO: add initial data
        allItems.add(new Item(0, "Overview", "Overview", "Overview",overview_short_desc, false, styled_overview_long_description));
        allItems.add(new Item(1, "1. Find your NOC", "1. NOC Completed","Find your NOC", noc_short_desc, false, styled_noc_long_description));
        allItems.add(new Item(2, "2. Get your ECA", "2. ECA Received","Get your ECA", eca_short_desc, false, styled_eca_long_description));
        allItems.add(new Item(3, "3. Language Tests", "3. Language Test Completed","Language Tests", language_short_desc, false, styled_language_long_desc));
        allItems.add(new Item(4, "4. Check Eligibility", "4. Eligiblity Completed","Check Eligibility", eligibility_short_desc, false, styled_eligibility_long_desc));
        allItems.add(new Item(5, "5. Calculate CRS Score", "5. Calculated CRS Score","Calculate CRS Score", crs_short_desc, false, styled_crs_long_desc));
        allItems.add(new Item(6, "6. Enter Express Entry Pool", "6. Entered Express Entry Pool", "Enter Express Entry Pool",pool_short_desc, false, styled_pool_long_desc));
        allItems.add(new Item(7, "7. Receive your ITA", "7. Received ITA","Receive your ITA" ,ita_short_desc, false, styled_ita_long_desc));
        allItems.add(new Item(8, "8. Document Checklist", "8. Document Checlist Completed","Document Checklist", checklist_short_desc, false, styled_checklist_long_desc));
        allItems.add(new Item(9, "9. E-APR Fees", "9. E-APR Fees Paid","E-APR Fees", fees_short_desc, false, styled_fees_long_desc));
        allItems.add(new Item(10, "10. AOR to PPR", "10. PPR Received","AOR to PPR",ppr_short_desc, false, styled_ppr_long_desc));
        allItems.add(new Item(11, "11. Prepare for Landing", "11. Welcome to Canada! ", "Prepare for Landing",landing_short_desc, false, styled_landing_long_desc));

        commonMistakeItems.add(new Item(0, "Passport Scan Missing/incomplete", "", false, spanned_styled_overview_long_description));
        commonMistakeItems.add(new Item(1, "Work experience description & duties do not match those of the NOC code. ", "", false, spanned_work_exp_desc_and_duties_desc));
        commonMistakeItems.add(new Item(2, "Not uploading Educational Credential Assessment (ECA).", "", false, spanned_not_uploading_eca_desc));
        commonMistakeItems.add(new Item(3, "Police Clearance Certificate (PCC) missing.", "", false, spanned_pcc_missing_desc));
        commonMistakeItems.add(new Item(4, "PCC is not scanned in color.", "", false, spanned_pcc_not_in_color_desc));
        commonMistakeItems.add(new Item(5, "Not having a few words explicitly written (Permanent, Full time, Non-seasonal) on job offers.", "", false, spanned_a_few_words_explicitly_written_desc));
        commonMistakeItems.add(new Item(6, "Submitting internet copies of bank statements that are not printed with the letterhead of the bank.", "", false, spanned_submitting_ban_statement_desc));
        commonMistakeItems.add(new Item(7, "Not uploading the English or French translation.", "", false, spanned_not_upload_english_french_translation_desc));
        commonMistakeItems.add(new Item(8, "Uploading only the translation and not the foreign-language document.", "", false, spanned_not_the_foreign_lang_desc));
        commonMistakeItems.add(new Item(9, "Canadian Education.", "", false, spanned_canadian_education_dec));
        commonMistakeItems.add(new Item(10, "Not Signing the Application Forms", "", false, spanned_not_signing_application_forms_desc));
        commonMistakeItems.add(new Item(11, "Paying the Fee Incorrectly", "", false, spanned_paying_the_fee_incorrectly_desc));
        commonMistakeItems.add(new Item(12, "Correct Size Photo", "", false, spanned_correct_size_photo_desc));
        commonMistakeItems.add(new Item(13, "Additional Forms for Certain Countries", "", false, spanned_additional_forms_for_certain_countries_desc));
        commonMistakeItems.add(new Item(14, "Providing Misinformation/Not Explaining Missing Information", "", false, spanned_providing_info_decs));
        commonMistakeItems.add(new Item(15, "Illegible Handwriting on handwritten immigration Application Forms", "", false, spanned_illegible_handwritten_desc));
    }

    public static Utils getInstance() {
        if (null == instance) {
            instance = new Utils();
        }
        return instance;
    }

    public static ArrayList<Item> getAllItems() {
        return allItems;
    }

    public static ArrayList<Item> getCommonMistakeItems() {
        return commonMistakeItems;
    }

    public Item getItemById(int id) {
        for (Item b : allItems) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public static Spanned getSingle_styled_checklist_long_desc() {
        return single_styled_checklist_long_desc;
    }

    public static Spanned getMarried_styled_checklist_long_desc() {
        return married_styled_checklist_long_desc;
    }
}
