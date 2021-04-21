package com.prod.treknation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_exp_crs.*
import kotlinx.android.synthetic.main.fragment_exp_crs.btnBackArrow
import kotlinx.android.synthetic.main.fragment_exp_crs.tvAge
import kotlinx.android.synthetic.main.fragment_exp_crs.tvListening


class ExpCRSFragment : Fragment() {

    private var onFragmentActionCallback: OnFragmentActionCallback? = null
    private var sharedPref: SharedPreferences? = null
    private var isSpouse: Boolean? = false

    public fun setAttachCallback(onFragmentActionCallback: OnFragmentActionCallback) {
        this.onFragmentActionCallback = onFragmentActionCallback
    }

    companion object {
        fun newInstance(): ExpCRSFragment {
            val frag = ExpCRSFragment()
            frag.arguments = Bundle()
            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_exp_crs, null, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)
        isSpouse = sharedPref?.getBoolean(CRSCalculatorFragment.SPOUSE_EXP, false)

        btnBackArrow.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        if(isSpouse==true){
            tvListening.visibility=View.GONE
            tvForeignExp.visibility=View.GONE
            sbforeignExp.visibility=View.GONE
            tvCertiOfQualifi.visibility=View.GONE
            cb_certi_qualification.visibility=View.GONE

        }

        if (isSpouse == true) {
            tvAge.setText(getString(R.string.spouse_experience))

            (activity as CRSCalculatorActivity?)?.getCRSUser()?.canWorkExpSpouse?.let { value ->
                sbcanadaian_experience.progress = value
                if (value == 5) {
                    tvWorkExp.text = "$value or more yrs"

                } else if (value == 1 || value == 0) {
                    tvWorkExp.text = "$value yr"
                } else {
                    tvWorkExp.text = "$value yrs"

                }
            }

            (activity as CRSCalculatorActivity?)?.getCRSUser()?.forWorkExpSpouse?.let { value ->
                sbforeignExp.progress = value
                if (value == 0) {
                    tvForeignExp.text = "0 yr"
                } else if (value == 1) {
                    tvForeignExp.text = "1 yr"
                } else if (value == 2) {
                    tvForeignExp.text = "2 yrs"
                } else if (value == 3) {
                    tvForeignExp.text = "3 or more yrs"
                }
            }

            tvAgeScore.text = ((activity as CRSCalculatorActivity?)?.workExpSpouse()
                    ?: 0).toString()
            val isChecked = (activity as CRSCalculatorActivity?)?.getCRSUser()?.certiOfQualiSpouse
                    ?: false
            when (isChecked) {
                true -> cb_certi_qualification_yes?.isChecked = true
                false -> cb_certi_qualification_no?.isChecked = true
            }
        } else {
            tvAge.setText(getString(R.string.experience))
            (activity as CRSCalculatorActivity?)?.getCRSUser()?.canWorkExp?.let { value ->
                sbcanadaian_experience.progress = value
                if (value == 5) {
                    tvWorkExp.text = "$value or more yrs"

                } else if (value == 0 || value == 1) {
                    tvWorkExp.text = "$value yr"
                } else {
                    tvWorkExp.text = "$value yrs"

                }
            }

            (activity as CRSCalculatorActivity?)?.getCRSUser()?.forWorkExp?.let { value ->
                sbforeignExp.progress = value
                if (value == 0) {
                    tvForeignExp.text = "0 yr"
                } else if (value == 1) {
                    tvForeignExp.text = "1 yr"
                } else if (value == 2) {
                    tvForeignExp.text = "2 yrs"
                } else if (value == 3) {
                    tvForeignExp.text = "3 or more yrs"
                }
            }

            tvAgeScore.text = (activity as CRSCalculatorActivity?)?.foreignWorkExp()?.plus((activity as CRSCalculatorActivity?)?.canadianWorkExp()
                    ?: 0)?.plus((activity as CRSCalculatorActivity?)?.workExpOnOtherParameters()
                    ?: 0).toString()
            val isChecked = (activity as CRSCalculatorActivity?)?.getCRSUser()?.certiOfQuali
                    ?: false
            when (isChecked) {
                true -> cb_certi_qualification_yes?.isChecked = true
                false -> cb_certi_qualification_no?.isChecked = true
            }
        }




        sbcanadaian_experience.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.d("Canadaian Experience", "Can exp$p1")
                if (p1 == 5) {
                    tvWorkExp.text = "$p1 or more yrs"

                } else if (p1 == 1 || p1 == 0) {
                    tvWorkExp.text = "$p1 yr"
                } else {
                    tvWorkExp.text = "$p1 yrs"
                }

                if (isSpouse == true) {
                    (activity as CRSCalculatorActivity?)?.getCRSUser()?.canWorkExpSpouse = p1
                    tvAgeScore.text = ((activity as CRSCalculatorActivity?)?.workExpSpouse()
                            ?: 0).toString()
                } else {
                    (activity as CRSCalculatorActivity?)?.getCRSUser()?.canWorkExp = p1
                    val canExp = (activity as CRSCalculatorActivity?)?.canadianWorkExp() ?: 0
                    val forExp = (activity as CRSCalculatorActivity?)?.foreignWorkExp() ?: 0
                    val other = (activity as CRSCalculatorActivity?)?.workExpOnOtherParameters()
                            ?: 0
                    tvAgeScore.text = canExp.plus(forExp).plus(other).toString()
                }


            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        sbforeignExp.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p1 == 0) {
                    tvForeignExp.text = "0 yr"
                } else if (p1 == 1) {
                    tvForeignExp.text = "1 yr"
                } else if (p1 == 2) {
                    tvForeignExp.text = "2 yrs"
                } else if (p1 == 3) {
                    tvForeignExp.text = "3 or more yrs"
                }


                if (isSpouse == true) {
                    (activity as CRSCalculatorActivity?)?.getCRSUser()?.forWorkExpSpouse = p1
                    tvAgeScore.text = ((activity as CRSCalculatorActivity?)?.workExpSpouse()
                            ?: 0).toString()
                } else {
                    (activity as CRSCalculatorActivity?)?.getCRSUser()?.forWorkExp = p1
                    val canExp = (activity as CRSCalculatorActivity?)?.canadianWorkExp() ?: 0
                    val forExp = (activity as CRSCalculatorActivity?)?.foreignWorkExp() ?: 0
                    val other = (activity as CRSCalculatorActivity?)?.workExpOnOtherParameters()
                            ?: 0
                    tvAgeScore.text = canExp.plus(forExp).plus(other).toString()
                }


            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        btnAddExp.setOnClickListener {
            if (isSpouse == true) {
                (activity as CRSCalculatorActivity?)?.getCRSUser()?.certiOfQualiSpouse = cb_certi_qualification_yes.isChecked
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.workExpSpouse().toString()

            } else {
                (activity as CRSCalculatorActivity?)?.getCRSUser()?.certiOfQuali = cb_certi_qualification_yes.isChecked
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.foreignWorkExp()?.plus((activity as CRSCalculatorActivity?)?.canadianWorkExp()
                        ?: 0)?.plus((activity as CRSCalculatorActivity?)?.workExpOnOtherParameters()
                        ?: 0).toString()
            }


            (activity as CRSCalculatorActivity?)?.updateCRSUser()
            activity?.supportFragmentManager?.popBackStack()
        }


        cb_certi_qualification_yes.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            if (isSpouse == true) {
                (activity as CRSCalculatorActivity?)?.getCRSUser()?.certiOfQualiSpouse = cb_certi_qualification_yes.isChecked
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.workExpSpouse().toString()

            } else {
                (activity as CRSCalculatorActivity?)?.getCRSUser()?.certiOfQuali = cb_certi_qualification_yes.isChecked
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.foreignWorkExp()?.plus((activity as CRSCalculatorActivity?)?.canadianWorkExp()
                        ?: 0)?.plus((activity as CRSCalculatorActivity?)?.workExpOnOtherParameters()
                        ?: 0).toString()
            }


        }
        )

    }

    fun getCanadianWorkWithoutYear(workExp: String): Int {
        return when (workExp) {
            "1 year" -> 1
            "2 years" -> 2
            "3 years" -> 3
            "4 years" -> 4
            "5 years+" -> 5
            "1 or 2 years" -> 2
            "3 years+" -> 3

            else -> 0
        }
   }


}