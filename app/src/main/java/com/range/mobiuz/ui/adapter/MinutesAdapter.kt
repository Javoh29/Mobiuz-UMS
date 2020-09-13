package com.range.mobiuz.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.elasticviews.ElasticCardView
import com.range.mobiuz.R
import com.range.mobiuz.data.db.entity.MinutesModel
import com.range.mobiuz.ui.fragment.SingleAction

/**
 * Created by Javoh on 15.08.2020.
 */

class MinutesAdapter(private val list: List<MinutesModel>, private val sAction: SingleAction) : RecyclerView.Adapter<MinutesAdapter.MinutesViewHolder>() {

    class MinutesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvValue: AppCompatTextView = view.findViewById(R.id.tvValue)
        val tvPrice: AppCompatTextView = view.findViewById(R.id.tvPrice)
        val cardBuy: ElasticCardView = view.findViewById(R.id.cardBuy)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MinutesViewHolder {
        return MinutesViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_single_container, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MinutesViewHolder, position: Int) {
        if (list[position].type == 1) {
            holder.tvValue.text = "${holder.tvValue.context.getString(R.string.text_sms)} ${list[position].name}"
        } else holder.tvValue.text = "${list[position].name} ${holder.tvValue.context.getString(R.string.text_minute)}"

        holder.tvPrice.text = list[position].price

        holder.cardBuy.setOnClickListener { sAction.itemClick(list[position].code) }
    }
}