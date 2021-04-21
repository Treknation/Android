package com.prod.treknation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_addi_points_crs.*


class AdditionalPointsCRSFragment : Fragment() {

    private var onFragmentActionCallback: OnFragmentActionCallback? = null

    public fun setAttachCallback(onFragmentActionCallback: OnFragmentActionCallback) {
        this.onFragmentActionCallback = onFragmentActionCallback
    }

    companion object {
        fun newInstance(): AdditionalPointsCRSFragment {
            val frag = AdditionalPointsCRSFragment()
            frag.arguments = Bundle()
            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_addi_points_crs, null, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBackArrow.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        (activity as CRSCalculatorActivity?)?.getCRSUser()?.additionalJobOffer?.let {
            setAdditionPointScore(it)
        }
        (activity as CRSCalculatorActivity?)?.getCRSUser()?.additionalPrCitizen?.let {
            setAdditionPointScore(it)
        }
        (activity as CRSCalculatorActivity?)?.getCRSUser()?.additionalNomination?.let {
            setAdditionPointScore(it)
        }

        rb_NOC.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                (activity as CRSCalculatorActivity?)?.getCRSUser()?.additionalJobOffer = rb_NOC.text.toString()
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.getAdditionalPointScore().toString()
                rb_OAB.isChecked = false

            } else {
                if (rb_OAB.isChecked == false && rb_NOC.isChecked == false) {
                    (activity as CRSCalculatorActivity?)?.getCRSUser()?.additionalJobOffer = ""
                }
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.getAdditionalPointScore().toString()

            }
        }

        rb_OAB.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                (activity as CRSCalculatorActivity?)?.getCRSUser()?.additionalJobOffer = rb_OAB.text.toString()
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.getAdditionalPointScore().toString()
                rb_NOC.isChecked = false

            } else {
                if (rb_OAB.isChecked == false && rb_NOC.isChecked == false) {
                    (activity as CRSCalculatorActivity?)?.getCRSUser()?.additionalJobOffer = ""
                }
                tvAgeScore.text = (activity as CRSCalculatorActivity?)?.getAdditionalPointScore().toString()

            }
        }

        rg_pr_citizen.setOnCheckedChangeListener { group, checkedId ->
            val rb = view.findViewById(checkedId) as RadioButton
            (activity as CRSCalculatorActivity?)?.getCRSUser()?.additionalPrCitizen = "citizen_" + rb.text.toString()
            tvAgeScore.text = (activity as CRSCalculatorActivity?)?.getAdditionalPointScore().toString()
        }

        rg_pr_nomination.setOnCheckedChangeListener { group, checkedId ->
            val rb = view.findViewById(checkedId) as RadioButton
            (activity as CRSCalculatorActivity?)?.getCRSUser()?.additionalNomination = "nomination_" + rb.text.toString()
            tvAgeScore.text = (activity as CRSCalculatorActivity?)?.getAdditionalPointScore().toString()
        }

        tvAgeScore.text = (activity as CRSCalculatorActivity).getAdditionalPointScore().toString()


        btnAddAdditionalPoints.setOnClickListener {
            (activity as CRSCalculatorActivity).updateCRSUser()
            tvAgeScore.text = (activity as CRSCalculatorActivity).getAdditionalPointScore().toString()
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    fun setAdditionPointScore(points: String) {
        return when (points) {
            "NOC - OO" -> rb_NOC.isChecked = true
            "NOC - O/A/B" -> rb_OAB.isChecked = true
            "citizen_Yes" -> rb_Yes.isChecked = true
            "nomination_Yes" -> rb_Yes_nomination.isChecked = true
            "nomination_No" -> rb_no_nomination.isChecked = true

            else -> {
                rb_no.isChecked = true
            }
        }
    }

}