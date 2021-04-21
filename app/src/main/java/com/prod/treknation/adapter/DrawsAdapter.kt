package com.prod.treknation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prod.treknation.DateFormatUtils
import com.prod.treknation.R
import com.prod.treknation.model.Draws
import kotlinx.android.synthetic.main.item_draws_list.view.*
import java.util.*


class DrawsAdapter(val items: ArrayList<Draws>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_draws_list, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position);

        holder.itemView.txtItemName.text = DateFormatUtils.DateFormat(item.dateTime)

        if(item.category.isNullOrBlank())
        holder.itemView.txtItemSub1.text = item.crsScoreOfTheLowestRank.toString()
        else
            holder.itemView.txtItemSub1.text = item.crsScoreOfTheLowestRank.toString()+" ("+item.category+")"


    }

}

