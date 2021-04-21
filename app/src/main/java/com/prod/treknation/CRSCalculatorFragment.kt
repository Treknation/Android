package com.prod.treknation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.prod.treknation.EventTracker.Companion.crs_relation_status
import com.prod.treknation.EventTracker.Companion.crs_total_score
import com.prod.treknation.EventTracker.Companion.logEvent
import com.prod.treknation.EventTracker.Companion.value
import kotlinx.android.synthetic.main.fragment_crs_calculator.*
import java.util.*

class CRSCalculatorFragment : Fragment() {

    private var marital: Boolean = false
    private var onFragmentActionCallback: OnFragmentActionCallback? = null

    public fun setAttachCallback(onFragmentActionCallback: OnFragmentActionCallback) {
        this.onFragmentActionCallback = onFragmentActionCallback
    }

    companion object {
        const val SPOUSE_EDU = "spouseEdu"
        const val SPOUSE_EXP = "spouseExp"
        const val SPOUSE_LANG = "spouseLang"

        fun newInstance(): CRSCalculatorFragment {
            val frag = CRSCalculatorFragment()
            frag.arguments = Bundle()
            return frag
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_crs_calculator, null, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        checkUser()

        btnSingle.setOnClickListener {
            btnSingle.background = ContextCompat.getDrawable(requireContext(), R.drawable.rect_button_line)
            btnMarried.background = null
            llMarried.visibility = View.GONE
            (activity as CRSCalculatorActivity?)?.getCRSUserInfo(false)
            marital = false
            val HashMap = HashMap<String, String>()
            HashMap[value] = "single"
            logEvent(crs_relation_status, HashMap)
            refreshScore()
        }
        btnMarried.setOnClickListener {
            val HashMap = HashMap<String, String>()
            HashMap[value] = "married"
            logEvent(crs_relation_status, HashMap)
            btnSingle.background = null
            btnMarried.background = ContextCompat.getDrawable(requireContext(), R.drawable.rect_button_line)
            llMarried.visibility = View.VISIBLE
            (activity as CRSCalculatorActivity?)?.getCRSUserInfo(true)
            marital = true

            refreshScore()
        }
        llAge.setOnClickListener {
            onFragmentActionCallback?.onAgeSelected()
        }
        llEdu.setOnClickListener {
            setSingleSharedPref()
            onFragmentActionCallback?.onEduSelected()
        }
        llLang.setOnClickListener {
            setSingleSharedPref()
            onFragmentActionCallback?.onLangSelected()
        }
        llExp.setOnClickListener {
            setSingleSharedPref()
            onFragmentActionCallback?.onExpSelected()
        }
        llAdditionalPoint.setOnClickListener {
            onFragmentActionCallback?.onAdditionalPointSelected()
        }
        llSpouseEdu.setOnClickListener {
            setMarriedSharedPref()
            onFragmentActionCallback?.onSpouseEduSelected()
        }
        llSpouseLang.setOnClickListener {
            setMarriedSharedPref()
            onFragmentActionCallback?.onSpouseLangSelected()
        }
        llSpouseExp.setOnClickListener {
            setMarriedSharedPref()
            onFragmentActionCallback?.onSpouseExpSelected()
        }
        btnBackArrow?.setOnClickListener {
            activity?.supportFragmentManager?.popBackStackImmediate()
            activity?.finish()
        }

        tvCrsRefresh.setOnClickListener {
            (activity as CRSCalculatorActivity?)?.clearData()
            (activity as CRSCalculatorActivity?)?.getCRSUserInfo(marital)
            refreshScore()
        }
    }

    private fun checkUser() {
        (activity as CRSCalculatorActivity?)?.getExistingUser()?.let {
            if (it.marital_status == true) {
                btnSingle.background = null
                btnMarried.background = ContextCompat.getDrawable(requireContext(), R.drawable.rect_button_line)
                llMarried.visibility = View.VISIBLE
                marital = false

                refreshScore()
            } else {

                btnSingle.background = ContextCompat.getDrawable(requireContext(), R.drawable.rect_button_line)
                btnMarried.background = null
                llMarried.visibility = View.GONE
                marital = true

                refreshScore()
            }

        } ?: run {
            (activity as CRSCalculatorActivity?)?.getCRSUserInfo(false)
        }
    }

    private fun setSingleSharedPref() {
        val sharedPreferences = requireActivity().getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(SPOUSE_EDU, false).apply()
        sharedPreferences.edit().putBoolean(SPOUSE_EXP, false).apply()
        sharedPreferences.edit().putBoolean(SPOUSE_LANG, false).apply()
    }

    private fun setMarriedSharedPref() {
        val sharedPreferences = requireActivity().getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(SPOUSE_EDU, true).apply()
        sharedPreferences.edit().putBoolean(SPOUSE_EXP, true).apply()
        sharedPreferences.edit().putBoolean(SPOUSE_LANG, true).apply()
    }

     override fun onResume() {
        super.onResume()
        checkUser()
    }

    fun refreshScore() {

        tvCrsScore.text = (activity as CRSCalculatorActivity?)?.calculateScore(llMarried.visibility == View.VISIBLE).toString()
        val HashMap = HashMap<String, String>()
        HashMap[value] = tvCrsScore.text as String
        logEvent(crs_total_score, HashMap)

        tvEduScore.text = (activity as CRSCalculatorActivity?)?.education().toString()
        tvLangScore.text = (activity as CRSCalculatorActivity?)?.calLang().toString()
        tvAgeScore.text = (activity as CRSCalculatorActivity?)?.age().toString()
        tvAdditionalPointScore.text = (activity as CRSCalculatorActivity?)?.getAdditionalPointScore().toString()
        tvExpScore.text = (activity as CRSCalculatorActivity?)?.workExp().toString()



        tvSpouseEduScore.text = (activity as CRSCalculatorActivity?)?.spsEducationOutsideCalculator().toString()
        tvSpouseLangScore.text = (activity as CRSCalculatorActivity?)?.calLangSpouse().toString()
        tvSpouseExpScore.text = (activity as CRSCalculatorActivity?)?.workExpSpouse().toString()

        HashMap.clear()
        HashMap[value] = tvAgeScore.text as String
        logEvent("crs_age_score", HashMap)

        HashMap.clear()
        HashMap[value] = tvEduScore.text as String
        logEvent("crs_education_score", HashMap)

        HashMap.clear()
        HashMap[value] = tvLangScore.text as String
        logEvent("crs_language_score", HashMap)

        HashMap.clear()
        HashMap[value] = tvExpScore.text as String
        logEvent("crs_experience_score", HashMap)

        HashMap.clear()
        HashMap[value] = tvAdditionalPointScore.text as String
        logEvent("crs_additional_point_score", HashMap)


        HashMap.clear()
        HashMap[value] = tvSpouseEduScore.text as String
        logEvent("crs_spouse_education_score", HashMap)

        HashMap.clear()
        HashMap[value] = tvSpouseLangScore.text as String
        logEvent("crs_spouse_language_score", HashMap)

        HashMap.clear()
        HashMap[value] = tvSpouseExpScore.text as String
        logEvent("crs_spouse_experience_score", HashMap)

    }
}