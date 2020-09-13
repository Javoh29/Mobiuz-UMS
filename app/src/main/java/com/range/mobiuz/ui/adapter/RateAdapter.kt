package com.range.mobiuz.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.elasticviews.ElasticCardView
import com.range.mobiuz.R
import com.range.mobiuz.data.db.entity.RateModel
import com.range.mobiuz.ui.fragment.RateAction

/**
 * Created by Javoh on 16.08.2020.
 */

class RateAdapter(private val list: List<RateModel>, private val rAction: RateAction) : RecyclerView.Adapter<RateAdapter.RateViewHolder>() {

    class RateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: AppCompatTextView = view.findViewById(R.id.tvTitle)
        val tvMothPrice: AppCompatTextView = view.findViewById(R.id.tvMothPrice)
        val tvSms: AppCompatTextView = view.findViewById(R.id.tvSmsCount)
        val tvMinutes: AppCompatTextView = view.findViewById(R.id.tvMinutes)
        val tvTraffic: AppCompatTextView = view.findViewById(R.id.tvMbCount)
        val cardOver: ElasticCardView = view.findViewById(R.id.cardOver)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        return RateViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_rate_container, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        if (rAction.getLang()) {
            holder.tvTitle.text = list[position].nameRu
        } else holder.tvTitle.text = list[position].name

        holder.tvMothPrice.text = "${list[position].price} ${holder.tvMothPrice.context.getString(R.string.text_sum)}"
        holder.tvSms.text = "${list[position].sms} ${holder.tvSms.context.getString(R.string.text_sms)}"
        holder.tvMinutes.text = "${list[position].minutes} ${holder.tvMinutes.context.getString(R.string.text_minute_small)}"
        holder.tvTraffic.text = "${list[position].packet} ${holder.tvTraffic.context.getString(R.string.text_mb)}"

        holder.cardOver.setOnClickListener { rAction.itemClick(list[position].code) }
    }
}