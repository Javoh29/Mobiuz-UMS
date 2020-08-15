package uz.tillo.umsdealer.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.tillo.umsdealer.R
import uz.tillo.umsdealer.utils.MovingImageView

class AdsViewAdapter: RecyclerView.Adapter<AdsViewAdapter.AdsViewHolder>() {

    class AdsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageView: MovingImageView = view.findViewById(R.id.imgAds)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        return AdsViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_ads_card_container, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        holder.imageView.setImageResource(R.drawable.img_ads_banner_1)
    }
}