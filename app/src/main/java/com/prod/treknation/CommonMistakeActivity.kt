package com.prod.treknation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_common_mistakes.*

class CommonMistakeActivity : AppCompatActivity() {

    private var arrayList: ArrayList<Item>? = null

    companion object {
        @JvmStatic
        fun launch(context: Context) {
            val intent = Intent(context, CommonMistakeActivity::class.java)
            context.startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_mistakes)

        arrayList = Utils.getCommonMistakeItems()
        initViews()
    }

    private fun initViews() {
        itemRecView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        val adapter = CommonMistakeAdapter(this, arrayList)
        itemRecView.adapter = adapter

        btnBackArrow.setOnClickListener {
            finish()
        }
    }
}