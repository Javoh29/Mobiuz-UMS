package com.range.mobiuz.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.elasticviews.ElasticCardView
import com.range.mobiuz.R
import com.range.mobiuz.data.db.entity.PacketModel
import com.range.mobiuz.ui.fragment.SingleAction

/**
 * Created by Javoh on 15.08.2020.
 */

class PacketsAdapter(private val list: List<PacketModel>, private val sAction: SingleAction) : RecyclerView.Adapter<PacketsAdapter.PacketsViewHolder>() {

    class PacketsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvValue: AppCompatTextView = view.findViewById(R.id.tvValue)
        val tvPrice: AppCompatTextView = view.findViewById(R.id.tvPrice)
        val cardBuy: ElasticCardView = view.findViewById(R.id.cardBuy)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacketsViewHolder {
        return PacketsViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_single_container, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PacketsViewHolder, position: Int) {
        if (list[position].type == 2) {
            holder.tvValue.text = "${list[position].name} ${holder.tvValue.context.getString(R.string.text_day)}"
        } else holder.tvValue.text = "${list[position].name} ${holder.tvValue.context.getString(R.string.text_mb)}"

        holder.tvPrice.text = list[position].price

        holder.cardBuy.setOnClickListener { sAction.itemClick(list[position].code) }
    }
}