package com.range.mobiuz.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.range.mobiuz.App.Companion.sale
import com.range.mobiuz.R
import com.range.mobiuz.data.db.entity.BannerModel
import com.range.mobiuz.data.repository.MobiuzRepository
import com.range.mobiuz.ui.adapter.AdsViewAdapter
import com.range.mobiuz.ui.base.ScopedFragment
import com.range.mobiuz.utils.UssdCodes
import com.range.mobiuz.utils.lazyDeferred
import com.range.mobiuz.utils.ussdCall
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import java.util.*


class HomeFragment : ScopedFragment(R.layout.fragment_home) {

    private val mobiuzRepository: MobiuzRepository by instance()
    private var timer: TimerTask? = null
    @SuppressLint("SimpleDateFormat")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
        bindSale()
        requestPermission()
    }


    private fun bindUI() = launch {
        lazyDeferred { mobiuzRepository.getBanners() }.value.await().observe(viewLifecycleOwner, {
            if (it == null) return@observe
            if (it.isNotEmpty()) {
                requireActivity().runOnUiThread {
                    bindBanner(it)
                }
            } else return@observe
        })

        if (unitProvider.getLang()) {
            imgLang.setImageResource(R.drawable.ic_rus)
        } else {
            imgLang.setImageResource(R.drawable.ic_uzb)
        }


        val dialogMore = Dialog(requireContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
        dialogMore.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogMore.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogMore.setContentView(R.layout.dialog_more)

        val tvTelegram: AppCompatTextView = dialogMore.findViewById(R.id.tvTelegram)
        val tvWallet: AppCompatTextView = dialogMore.findViewById(R.id.tvWallet)
        val tvExpenses: AppCompatTextView = dialogMore.findViewById(R.id.tvExpenses)
        val tvGraph: AppCompatTextView = dialogMore.findViewById(R.id.tvGraph)
        val tvMyNumber: AppCompatTextView = dialogMore.findViewById(R.id.tvMyNumber)
        val tvAllNumbers: AppCompatTextView = dialogMore.findViewById(R.id.tvAllNumbers)
        val tvCheck: AppCompatTextView = dialogMore.findViewById(R.id.tvCheck)
        val tvOtherApps: AppCompatTextView = dialogMore.findViewById(R.id.tvOtherApps)

        tvTelegram.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/umsuzb2")))
        }

        tvWallet.setOnClickListener {
            ussdCall(UssdCodes.lastPayment, it.context)
        }

        tvExpenses.setOnClickListener {
            ussdCall(UssdCodes.myDisCharge, it.context)
        }

        tvGraph.setOnClickListener {
            ussdCall(UssdCodes.RemainTrafBtn, it.context)
        }

        tvMyNumber.setOnClickListener {
            ussdCall(UssdCodes.myNumber, it.context)
        }

        tvAllNumbers.setOnClickListener {
            ussdCall(UssdCodes.allMyNumbers, it.context)
        }

        tvCheck.setOnClickListener {
            ussdCall(UssdCodes.CheckActServ, it.context)
        }

        tvOtherApps.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=uz.nisd.ussduzbekistan2020ucellbeelineuzmobilemobiuzums")))
        }

        cardInternet.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToInternetFragment())
        }

        cardSms.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToMinutesFragment())
        }

        cardRate.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToRateFragment())
        }

        cardService.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToServiceFragment())
        }

        cardMore.setOnClickListener {
            dialogMore.show()
        }

        cardBalance.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToUssdCodesFragment())
        }

        layoutLang.setOnClickListener {
            unitProvider.saveLang(!unitProvider.getLang())
            requireActivity().recreate()
        }

        layoutUser.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://ip.mobi.uz/selfcare/")))
        }

        btnSend.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=uz.tillo.umsdealer")))
        }

    }

    private fun bindBanner(list: List<BannerModel>) {
        adsViewPager.adapter = AdsViewAdapter(list)
        adsViewPager.currentItem = 1
        adsViewPager.clipToPadding = false
        adsViewPager.clipChildren = false
        adsViewPager.offscreenPageLimit = 3
        adsViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePagerTransformer = CompositePageTransformer()
        compositePagerTransformer.addTransformer(MarginPageTransformer(20))
        compositePagerTransformer.addTransformer { page, position ->
            val r: Float = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.25f
        }

        adsViewPager.setPageTransformer(compositePagerTransformer)

        var page = 1

        timer = object : TimerTask() {
            override fun run() {
                requireActivity().runOnUiThread {
                    adsViewPager.currentItem = page % list.size
                }
                page++
            }
        }
        Timer().schedule(timer, 0, 7000)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_PHONE_STATE
                ),
                1
        )
    }

    private fun bindSale() = launch {
        sale = lazyDeferred { mobiuzRepository.getSale() }.value.await()
        if (sale != null) {
            when(sale?.sale) {
                "1" -> saleInternet.visibility = View.VISIBLE
                "2" -> saleMinute.visibility = View.VISIBLE
            }
            if (sale?.code != "no") {
                saleRate.visibility = View.VISIBLE
            }
        }
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }

}