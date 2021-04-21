package com.prod.treknation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prod.treknation.EventTracker.Companion.ee_draws
import com.prod.treknation.EventTracker.Companion.logEvent
import com.prod.treknation.adapter.DrawsAdapter
import com.prod.treknation.model.Draws
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class ExpressEntryResultsActivity : AppCompatActivity() {
    private var db: FirebaseFirestore? = null
    private var btnBackArrow: ImageView? = null
    private var txtOutsideCanada: TextView? = null
    private var rcLastDrawsRecyclerView: RecyclerView? = null
    private var mDrawsAdapter: DrawsAdapter? = null
    private val mDrawsList = ArrayList<Draws>()
    private var txtScore: TextView? = null
    private var txtHint: TextView? = null
    private var txtDrawDate: TextView? = null
    private var txtDrawInvited: TextView? = null
    private val TAG = "ExpressEntryResultsActivity"
    private var progress: ProgressBar? = null

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_express_entry)
        initViews()
        initData()
        btnBackArrow?.setOnClickListener { finish() }

        val HashMap = HashMap<String, String>()
        logEvent(ee_draws, HashMap)
    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        progress?.visibility = View.VISIBLE
        db?.collection("eeDraws")
                ?.get()
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        mDrawsList.clear()
                        for (document in task.result!!) {
                            Log.d(TAG, "document:  " + document.toString())
                            val draws = document.toObject(Draws::class.java)
                            mDrawsList.add(draws)
                        }

                        if (!mDrawsList.isEmpty()) {
                            mDrawsList.sortByDescending { it.dateTime }
                            mDrawsList.first().let {
                                txtScore?.text = it.crsScoreOfTheLowestRank?.toString()
                                txtHint?.text = it.category
                                txtDrawDate?.text = DateFormatUtils.DateFormat(it.dateTime)
                                txtDrawInvited?.text = it.noOfTheInvitationsIssued?.toString()+" Invited"
                                mDrawsList.remove(it)
                            }
                        }
                        mDrawsAdapter?.notifyDataSetChanged()
                        progress?.visibility = View.GONE


                    } else {
                        Log.d(TAG, "Error getting documents: ", task.exception)
                        progress?.visibility = View.GONE

                    }
                }



    }

    private fun initViews() {

        db = FirebaseFirestore.getInstance()
        btnBackArrow = findViewById(R.id.btnBackArrow)
        rcLastDrawsRecyclerView = findViewById(R.id.rc_last_draws)
        txtScore = findViewById(R.id.txt_score)
        txtDrawDate = findViewById(R.id.txt_draw_date)
        txtHint = findViewById(R.id.txt_hint)
        txtDrawInvited = findViewById(R.id.txt_draw_invited)
        progress = findViewById(R.id.progress)
        mDrawsAdapter = DrawsAdapter(mDrawsList, this)
        rcLastDrawsRecyclerView?.setLayoutManager(LinearLayoutManager(this))
        rcLastDrawsRecyclerView?.setAdapter(mDrawsAdapter)

    }
}