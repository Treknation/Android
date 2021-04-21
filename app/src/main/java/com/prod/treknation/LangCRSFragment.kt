package com.prod.treknation

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.prod.treknation.crs.Language
import com.prod.treknation.model.CLB
import kotlinx.android.synthetic.main.fragment_lang_crs.*
import kotlin.collections.ArrayList


class LangCRSFragment : Fragment() {

    private var isPrimary: Boolean = true
    private var onFragmentActionCallback: OnFragmentActionCallback? = null
    private var mLanguage: Language? = null
    private var sharedPref: SharedPreferences? = null
    private var isSpouse: Boolean? = false


    public fun setAttachCallback(onFragmentActionCallback: OnFragmentActionCallback) {
        this.onFragmentActionCallback = onFragmentActionCallback
    }

    companion object {
        var Primary: String = "Primary"
        var Secondary: String = "Secondary"
        fun newInstance(): LangCRSFragment {
            val frag = LangCRSFragment()
            frag.arguments = Bundle()
            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_lang_crs, null, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)
        isSpouse = sharedPref?.getBoolean(CRSCalculatorFragment.SPOUSE_LANG, false)

        if (isSpouse == true) {
            btnPrimary.visibility = View.GONE
            btnSecondary.visibility = View.GONE
            mLanguage = (activity as CRSCalculatorActivity?)?.getLanguage(CRSCalculatorFragment.SPOUSE_LANG);
            mLanguage?.LanguageName?.let {
                spinnerPrimary.setSelection(resources.getStringArray(R.array.secondary_primaryLang).indexOf(it))
            }

            tvAge.setText(getString(R.string.spouse_language))

        } else {
            tvAge.setText(getString(R.string.language))

            mLanguage = (activity as CRSCalculatorActivity?)?.getLanguage(Primary);
            mLanguage?.LanguageName?.let {
                spinnerPrimary.setSelection(resources.getStringArray(R.array.secondary_primaryLang).indexOf(it))
            }
        }
        setSpinnerAdapter()

        btnPrimary.setOnClickListener {
            btnPrimary.background = ContextCompat.getDrawable(requireContext(), R.drawable.rect_button_line)
            btnSecondary.background = null
            isPrimary = true
            setSpinnerAdapter()
            clear()
            mLanguage = (activity as CRSCalculatorActivity?)?.getLanguage(Primary, spinnerPrimary.selectedItem.toString());
            mLanguage?.LanguageName.let { selected ->
                if (selected == null)
                    init(spinnerPrimary.selectedItem.toString())
                else
                    init(selected)
            }
        }

        btnSecondary?.setOnClickListener {
            btnSecondary.background = ContextCompat.getDrawable(requireContext(), R.drawable.rect_button_line)
            btnPrimary.background = null
            isPrimary = false
            setSpinnerAdapter()
            clear()
            mLanguage = (activity as CRSCalculatorActivity?)?.getLanguage(Secondary, spinnerSecondary.selectedItem.toString());
            mLanguage?.LanguageName.let { selected ->
                if (selected == null)
                    init(spinnerSecondary.selectedItem.toString())
                else
                    init(selected)
            }
        }

        btnBackArrow.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()


        }

        btnAddAge.setOnClickListener {
            score()
            activity?.supportFragmentManager?.popBackStack()
        }

        sb_Listening.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                score()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // TODO Auto-generated method stub
                if (mLanguage?.LanguageName.equals("IELTS"))
                    tvListeningScore.text = ((progress.toFloat() / 2)).toString()
                else
                    tvListeningScore.text = progress.toString()
            }
        })

        sb_Reading.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                score()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // TODO Auto-generated method stub
                if (mLanguage?.LanguageName.equals("IELTS"))
                    tvReadingScore.text = ((progress.toFloat() / 2)).toString()
                else
                    tvReadingScore.text = progress.toString()
            }
        })



        sb_Writing.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                score()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // TODO Auto-generated method stub
                if (mLanguage?.LanguageName.equals("IELTS"))
                    tvWritingeScore.text = ((progress.toFloat() / 2)).toString()
                else
                    tvWritingeScore.text = progress.toString()
            }
        })

        sb_speaking.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                score()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // TODO Auto-generated method stub

                if (mLanguage?.LanguageName.equals("IELTS"))
                    tvSpeakingScore.text = ((progress.toFloat() / 2)).toString()
                else
                    tvSpeakingScore.text = progress.toString()
            }
        })


        spinnerPrimary?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (spinnerPrimary.visibility == View.VISIBLE) {
                    clear()
                    when (resources.getStringArray(R.array.secondary_primaryLang).get(position)) {
                        "IELTS" -> {
                            mLanguage?.LanguageName = "IELTS"
                            init("IELTS")

                            spinnerSecondary?.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.secondaryLang))

                        }
                        "CELPIP" -> {
                            mLanguage?.LanguageName = "CELPIP"
                            init("CELPIP")

                            spinnerSecondary?.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.secondaryLang))

                        }

                        "TEF Canada" -> {
                            mLanguage?.LanguageName = "TEF Canada"
                            init("TEF Canada")
                            spinnerSecondary?.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.primaryLang))


                        }

                        "TCF Canada" -> {
                            mLanguage?.LanguageName = "TCF Canada"
                            init("TCF Canada")
                            spinnerSecondary?.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.primaryLang))

                        }

                    }
                }
                score()
            }

        }


        spinnerSecondary?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                clear()
                when (parent?.getItemAtPosition(position).toString()) {

                    "IELTS" -> {
                        if (spinnerSecondary.visibility == View.VISIBLE) {
                            mLanguage?.LanguageName = "IELTS"
                            init("IELTS")

                        }
                    }
                    "CELPIP" -> {
                        if (spinnerSecondary.visibility == View.VISIBLE) {
                            mLanguage?.LanguageName = "CELPIP"
                            init("CELPIP")
                        }
                    }

                    "TEF Canada" -> {
                        if (spinnerSecondary.visibility == View.VISIBLE) {
                            mLanguage?.LanguageName = "TEF Canada"
                            init("TEF Canada")
                        }
                    }

                    "TCF Canada" -> {
                        if (spinnerSecondary.visibility == View.VISIBLE) {
                            mLanguage?.LanguageName = "TCF Canada"
                            init("TCF Canada")
                        }
                    }
                }
                score()
            }

        }

    }


    private fun setSpinnerAdapter() {
        if (isPrimary) {
            rlSpinnerPrimary.visibility = View.VISIBLE
            rlSpinnerSecondary.visibility = View.GONE

        } else {
            rlSpinnerSecondary.visibility = View.VISIBLE
            rlSpinnerPrimary.visibility = View.GONE


        }
    }

    private fun score() {

        mLanguage?.listening = sb_Listening.progress.toString()
        mLanguage?.reading = sb_Reading.progress.toString()
        mLanguage?.writing = sb_Writing.progress.toString()
        mLanguage?.speaking = sb_speaking.progress.toString()

        val numbers: MutableList<CLB> = ArrayList()
        mLanguage?.listening?.let {
            numbers.add(CLB(it.toDouble(), "listening"))
        }
        mLanguage?.reading?.let {
            numbers.add(CLB(it.toDouble(), "reading"))
        }
        mLanguage?.writing?.let {
            numbers.add(CLB(it.toDouble(), "writing"))
        }
        mLanguage?.speaking?.let {
            numbers.add(CLB(it.toDouble(), "speaking"))
        }

        if (numbers.isEmpty())
            numbers.add(CLB(0.0, ""))

        var minScore = numbers.minBy { it.count }
        var total = 0
        var minClb = 0

        numbers.forEach { selection ->
            var clb = 0
            mLanguage?.let {
                when (mLanguage?.LanguageName) {
                    "IELTS" -> {
                        clb = (activity as CRSCalculatorActivity?)?.getCRS()?.IELTS(selection) ?: 0
                        minClb = (activity as CRSCalculatorActivity?)?.getCRS()?.IELTS(minScore)
                                ?: 0


                    }
                    "CELPIP" -> {
                        clb = (activity as CRSCalculatorActivity?)?.getCRS()?.CELPIP(selection) ?: 0
                        minClb = (activity as CRSCalculatorActivity?)?.getCRS()?.CELPIP(minScore)
                                ?: 0


                    }

                    "TEF Canada" -> {

                        clb = (activity as CRSCalculatorActivity?)?.getCRS()?.TEF_Canada(selection)
                                ?: 0
                        minClb = (activity as CRSCalculatorActivity?)?.getCRS()?.TEF_Canada(minScore)
                                ?: 0


                    }

                    "TCF Canada" -> {

                        clb = (activity as CRSCalculatorActivity?)?.getCRS()?.TCF_Canada(selection)
                                ?: 0
                        minClb = (activity as CRSCalculatorActivity?)?.getCRS()?.TCF_Canada(minScore)
                                ?: 0


                    }

                }

                when (selection.name) {

                    "listening" -> {
                        mLanguage?.listeningCLB = clb
                        Log.d("CLB:", selection.name + ">" + clb.toString())

                    }
                    "reading" -> {
                        mLanguage?.readingCLB = clb
                        Log.d("CLB:", selection.name + ">" + clb.toString())

                    }
                    "writing" -> {
                        mLanguage?.writingCLB = clb
                        Log.d("CLB:", selection.name + ">" + clb.toString())

                    }

                    "speaking" -> {
                        mLanguage?.speakingCLB = clb
                        Log.d("CLB:", selection.name + ">" + clb.toString())

                    }

                }
            }
        }


        mLanguage?.let { it1 -> (activity as CRSCalculatorActivity?)?.updateLanguage(it1) };


        if (isSpouse == true)
            tvScore.text = (activity as CRSCalculatorActivity?)?.calLangSpouse().toString()
        else
            tvScore.text = (activity as CRSCalculatorActivity?)?.calLang().toString()

        (activity as CRSCalculatorActivity?)?.updateCRSUser();


    }

    @SuppressLint("NewApi")
    private fun init(string: String) {
        var max: Int = 0
        when (string) {
            "IELTS" -> {
                /* Reading: 0, 3 to 9 in increment of 0.5
                 Listening: 0, 3 to 9 in increment of 0.5
                 Writing: 0, 3 to 9 in increment of 0.5
                 Speaking:0, 3 to 9 in increment of 0.5*/

                sb_speaking.min = 6
                sb_speaking.max = 18

                sb_Listening.min = 6
                sb_Listening.max = 18

                sb_Reading.min = 6
                sb_Reading.max = 18

                sb_Writing.min = 6
                sb_Writing.max = 18

            }
            "CELPIP" -> {
                max = 10

                sb_speaking.min = 0
                sb_Listening.min = 0
                sb_Reading.min = 0
                sb_Writing.min = 0

                sb_speaking.max = 12
                sb_Listening.max = 12
                sb_Reading.max = 12
                sb_Writing.max = 12

                /* CELPIP
                 Reading: 0 to 12 in increment of 1
                 Listening: 0 to 12 in increment of 1
                 Writing: 0 to 12 in increment of 1
                 Speaking: 0 to 12 in increment of 1*/

            }

            "TEF Canada" -> {
                /* Reading: 0, 120- 300 in increment of 1
                 Listening: 0, 144-360 in increment of 1
                 Writing: 0, 180 - 450 in increment of 1
                 Speaking: 0, 180 - 450 in increment of 1*/
                sb_speaking.max = 450
                sb_speaking.min = 180

                sb_Reading.max = 300
                sb_Reading.min = 120

                sb_Writing.max = 450
                sb_Writing.min = 180

                sb_Listening.max = 360
                sb_Listening.min = 144

            }

            "TCF Canada" -> {
                /* TCF
                 Reading - 0, 342 - 699 in increment of 1
                 Listening - 0, 331 - 699 in increment of 1
                 Writing - 0, 4 - 20 in increment of 1
                 Speaking - 0, 4 - 20 in increment of 18*/

                sb_speaking.min = 4
                sb_Listening.min = 331
                sb_Reading.min = 342
                sb_Writing.min = 4

                sb_speaking.max = 20
                sb_Listening.max = 699
                sb_Reading.max = 699
                sb_Writing.max = 20

            }

        }

        setData()

    }

    private fun setData() {

        if (isPrimary) {
            if (isSpouse == true)
                mLanguage = (activity as CRSCalculatorActivity?)?.getLanguage(CRSCalculatorFragment.SPOUSE_LANG, spinnerPrimary.selectedItem.toString());
            else
                mLanguage = (activity as CRSCalculatorActivity?)?.getLanguage(Primary, spinnerPrimary.selectedItem.toString());

        } else
            mLanguage = (activity as CRSCalculatorActivity?)?.getLanguage(Secondary, spinnerSecondary.selectedItem.toString());


        mLanguage?.speaking?.let {
            it.let { it1 ->
                sb_speaking.setProgress(it1.toInt())

                if (mLanguage?.LanguageName.equals("IELTS"))
                    tvSpeakingScore.text = (it1.toFloat() / 2).toString()
                else
                    tvSpeakingScore.text = it

            }
        } ?: run {
            sb_speaking.progress = 0
            tvSpeakingScore.text = "0"
        }

        mLanguage?.writing?.let {
            it.let { it1 ->
                sb_Writing.setProgress(it1.toInt())

                if (mLanguage?.LanguageName.equals("IELTS"))
                    tvWritingeScore.text = (it1.toFloat() / 2).toString()
                else
                    tvWritingeScore.text = it

            }
        } ?: run {
            sb_Writing.progress = 0
            tvWritingeScore.text = "0"
        }

        mLanguage?.reading?.let {
            it.let { it1 ->
                sb_Reading.setProgress(it1.toInt())
                if (mLanguage?.LanguageName.equals("IELTS"))
                    tvReadingScore.text = (it1.toFloat() / 2).toString()
                else
                    tvReadingScore.text = it

            }
        } ?: run {
            sb_Reading.progress = 0
            tvReadingScore.text = "0"

        }

        mLanguage?.listening?.let {
            it.let { it1 ->
                sb_Listening.setProgress(it1.toInt())

                if (mLanguage?.LanguageName.equals("IELTS"))
                    tvListeningScore.text = (it1.toFloat() / 2).toString()
                else
                    tvListeningScore.text = it

            }
        } ?: run {
            sb_Listening.progress = 0
            tvListeningScore.text = "0"
        }


        if (isPrimary) {
            mLanguage?.LanguageName?.let {
                spinnerPrimary.setSelection(resources.getStringArray(R.array.secondary_primaryLang).indexOf(it))
            }

        } else {
            mLanguage?.LanguageName?.let {
                if (resources.getStringArray(R.array.secondaryLang).contains(it))
                    spinnerSecondary.setSelection(resources.getStringArray(R.array.secondaryLang).indexOf(it))
                else
                    spinnerSecondary.setSelection(resources.getStringArray(R.array.primaryLang).indexOf(it))
            }

        }

        if (isSpouse == true)
            tvScore.text = (activity as CRSCalculatorActivity?)?.calLangSpouse().toString()
        else
            tvScore.text = (activity as CRSCalculatorActivity?)?.calLang().toString()

    }

    fun clear() {

        mLanguage?.listening = "0"
        mLanguage?.reading = "0"
        mLanguage?.writing = "0"
        mLanguage?.speaking = "0"

        tvListeningScore.text = "0"
        tvReadingScore.text = "0"
        tvWritingeScore.text = "0"
        tvSpeakingScore.text = "0"


        sb_speaking.max = 1000
        sb_Listening.max = 1000
        sb_Reading.max = 1000
        sb_Writing.max = 1000


    }

}


