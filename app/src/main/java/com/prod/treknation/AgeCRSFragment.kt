package com.prod.treknation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_age_crs.*

class AgeCRSFragment : Fragment() {

    private var onFragmentActionCallback: OnFragmentActionCallback? = null

    public fun setAttachCallback(onFragmentActionCallback: OnFragmentActionCallback) {
        this.onFragmentActionCallback = onFragmentActionCallback
    }

    companion object {
        fun newInstance(): AgeCRSFragment {
            val frag = AgeCRSFragment()
            frag.arguments = Bundle()
            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_age_crs, null, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        npAge.minValue = 17
        npAge.maxValue = 45
        npAge.wrapSelectorWheel = false;


        val list = ArrayList<String>()
        for (i in 17 until 46) {
            if (i == 45)
                list.add(i.toString() + "+")
            else
                list.add(i.toString())
        }
        npAge.displayedValues = list.toTypedArray()
        (activity as CRSCalculatorActivity?)?.getCRSUser()?.age?.let {
            npAge.value = it.toInt()
            tvAgeScore.text = (activity as CRSCalculatorActivity?)?.age().toString()

        }

        npAge.setOnValueChangedListener { numberPicker, i, i2 ->
            tvAgeScore.text = (activity as CRSCalculatorActivity?)?.age(i2).toString()
        }

        btnBackArrow.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        btnAddAge.setOnClickListener {
            (activity as CRSCalculatorActivity?)?.getCRSUser()?.age = npAge.value.toString()
            tvAgeScore.text = (activity as CRSCalculatorActivity?)?.age().toString()
            activity?.supportFragmentManager?.popBackStack()
        }

    }

}