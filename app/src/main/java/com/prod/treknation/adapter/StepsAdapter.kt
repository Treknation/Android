package com.prod.treknation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prod.treknation.Item
import com.prod.treknation.R
import kotlinx.android.synthetic.main.item_steps_list.view.*
import java.util.*


class StepsAdapter(val items : ArrayList<Item>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_steps_list, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item=items.get(position);

        holder?.itemView.txtItemName.text =item.itemNameWithoutSequencesNumber

        if(item.isViewed()){
            holder?.itemView.image.setImageResource(R.drawable.ic_color_complet)
            holder?.itemView.txtStep.text="Completed"

        }else{
            holder?.itemView.image.setImageResource(R.drawable.ic_forward_arrow)
            holder?.itemView.txtStep.text="Next"



        }
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
}