package com.prod.treknation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_edu_crs.*


class EduCRSFragment : Fragment() {

    private var onFragmentActionCallback: OnFragmentActionCallback? = null
    private var sharedPref: SharedPreferences? = null

    public fun setAttachCallback(onFragmentActionCallback: OnFragmentActionCallback) {
        this.onFragmentActionCallback = onFragmentActionCallback
    }

    companion object {
        fun newInstance(): EduCRSFragment {
            val frag = EduCRSFragment()
            frag.arguments = Bundle()
            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_edu_crs, null, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)

        btnBackArrow.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        if (sharedPref?.getBoolean(CRSCalculatorFragment.SPOUSE_EDU, false) == false) {
            (activity as CRSCalculatorActivity?)?.getCRSUser()?.educationOutsideCanada?.let {
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.education().toString()
                setEducationOutsideCanadaSelection(it)
            }

            tvAge.setText(getString(R.string.education))

            (activity as CRSCalculatorActivity?)?.getCRSUser()?.educationInsideCanada?.let {
                educationInsideCanadaSelection(it)
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.education().toString()

            }

            rg_outside_canada.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId -> // checkedId is the RadioButton selected
                val rb = view.findViewById(checkedId) as RadioButton
                (activity as CRSCalculatorActivity?)?.getCRSUser()?.educationOutsideCanada = rb.text.toString()
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.education().toString()
            })

            gr_inside_canada.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId -> // checkedId is the RadioButton selected
                val rb = view.findViewById(checkedId) as RadioButton
                (activity as CRSCalculatorActivity?)?.getCRSUser()?.educationInsideCanada = rb.text.toString()
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.education().toString()
            })

        } else {
            clInsideCanada.visibility = View.GONE
            (activity as CRSCalculatorActivity?)?.getCRSUser()?.spsEducationOutsideCanada?.let {
                setEducationOutsideCanadaSelection(it)
            }
            tvAge.setText(getString(R.string.spouse_education))

            rg_outside_canada.setOnCheckedChangeListener { group, checkedId -> // checkedId is the RadioButton selected
                val rb = view.findViewById(checkedId) as RadioButton
                (activity as CRSCalculatorActivity?)?.getCRSUser()?.spsEducationOutsideCanada = rb.text.toString()
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.spsEducationOutsideCalculator().toString()
            }
        }

        btnAdd.setOnClickListener {
            tvAgeScore.text = (activity as CRSCalculatorActivity?)?.education().toString()
            (activity as CRSCalculatorActivity?)?.updateCRSUser()
            activity?.supportFragmentManager?.popBackStack()
        }
    }


    public fun setEducationOutsideCanadaSelection(education: String) {
        when (education) {
            "Less than secondary school (high school)" -> cb_HighSchool.isChecked = true
            "Secondary diploma (high school graduation)" -> cb_secondary_diploma_high_school_graduation.isChecked = true
            "One-year degree, diploma or certificate from a university, college, trade or technical school, or other institute" -> cb_one_year_degree_diploma_or_certificate.isChecked = true
            "Two-year program at a university, college, trade or technical school, or other institute" -> cb_two_year_program_at_a_university_college.isChecked = true
            "Bachelor's degree OR a three or more year program at a university, college, trade or technical school, or other institute" -> cb_bachelor_s_degree_or_a_three.isChecked = true
            "Two or more certificates, diplomas, or degrees. One must be for a program of three or more years" -> cb_two_or_more_certificates_diplomas.isChecked = true
            "Master\'s degree, OR professional degree needed to practice in a licensed profession (For “professional degree,” the degree program must have been in: medicine, veterinary medicine, dentistry, optometry, law, chiropractic medicine, or pharmacy.)" -> cb_master_s_degree_or_professional_degree_needed_.isChecked = true
            "Doctoral level university degree (Ph.D.)" -> cb_doctoral_level_university_degree_ph_d.isChecked = true
            else -> {
            }

        }

    }


    public fun educationInsideCanadaSelection(education: String) {
        when (education) {
            "Secondary High School" -> cb_HighSchool_inside_canada.isChecked = true
            "One or two year diploma or certificate" -> cb_one_two_year_dip_feg_inside_canada.isChecked = true
            "Degree, diploma or certificate of 3 years or longer or a Master’s profession or Doctoral degree of at least 1 academic year" -> cb_3plus_inside_canada.isChecked = true
        }

    }

}