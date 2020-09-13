package com.range.mobiuz.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.elasticviews.ElasticCardView
import com.range.mobiuz.R
import com.range.mobiuz.data.db.entity.BannerModel
import com.range.mobiuz.utils.MovingImageView

class AdsViewAdapter(private val list: List<BannerModel>): RecyclerView.Adapter<AdsViewAdapter.AdsViewHolder>() {

    class AdsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageView: MovingImageView = view.findViewById(R.id.imgAds)
        val cardBanner: ElasticCardView = view.findViewById(R.id.cardBanner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        return AdsViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_ads_card_container, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        holder.imageView.setImageResource(list[position].image)
        holder.cardBanner.setOnClickListener {
            it.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(list[position].url)))
        }
    }
}