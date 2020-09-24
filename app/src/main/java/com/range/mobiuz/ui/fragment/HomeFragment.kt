package com.range.mobiuz.ui.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.FileProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import com.range.mobiuz.App.Companion.isLoaded
import com.range.mobiuz.BuildConfig
import com.range.mobiuz.R
import com.range.mobiuz.data.db.entity.BannerModel
import com.range.mobiuz.data.db.entity.LangModel
import com.range.mobiuz.data.db.entity.VersionModel
import com.range.mobiuz.data.repository.MobiuzRepository
import com.range.mobiuz.ui.adapter.AdsViewAdapter
import com.range.mobiuz.ui.base.ScopedFragment
import com.range.mobiuz.utils.DownloadDialog
import com.range.mobiuz.utils.UssdCodes
import com.range.mobiuz.utils.lazyDeferred
import com.range.mobiuz.utils.ussdCall
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : ScopedFragment(R.layout.fragment_home) {

    private val mobiuzRepository: MobiuzRepository by instance()
    private var timer: TimerTask? = null
    private var dialog = DownloadDialog()
    @SuppressLint("SimpleDateFormat")
    private var simpleDate = SimpleDateFormat("dd.MM.yy")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
        loadData()
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadData() = launch(Dispatchers.IO) {
        if (unitProvider.getSaveDate() != simpleDate.format(Date())){
            if (unitProvider.isOnline()) {
                bindToast(lazyDeferred { mobiuzRepository.fetchingAllData() }.value.await())
            } else dialog.dismiss()
            isLoaded = false
        }else dialog.dismiss()
    }

    private fun bindUI() {
        dialog.isCancelable = false
        dialog.show(activity?.supportFragmentManager!!, "DownloadDialog")
        val list: ArrayList<BannerModel> = ArrayList()
        list.add(BannerModel(image = R.drawable.img_banner1, url = "https://mobi.uz/uz/messaging/minutes/16993/"))
        list.add(BannerModel(image = R.drawable.img_banner2, url = "https://mobi.uz/uz/internet/mobile_internet/9837/"))
        list.add(BannerModel(image = R.drawable.img_banner3, url = "https://mobi.uz/uz/tariff/liketalk/21158/"))
        list.add(BannerModel(image = R.drawable.img_banner4, url = "https://mobi.uz/uz/support/blocking_sim/21106/"))
        list.add(BannerModel(image = R.drawable.img_banner5, url = "https://mobi.uz/uz/tariff/liketalk/20349/"))
        list.add(BannerModel(image = R.drawable.img_banner6, url = "https://mobi.uz/uz/tariff/liketalk/20025/"))
        list.add(BannerModel(image = R.drawable.img_banner7, url = "https://mobi.uz/uz/tariff/liketalk/20347/"))
        list.add(BannerModel(image = R.drawable.img_banner8, url = "https://mobi.uz/uz/tariff/liketalk/20033/"))
        list.add(BannerModel(image = R.drawable.img_banner9, url = "https://mobi.uz/uz/tariff/liketalk/20244/"))
        adsViewPager.adapter = AdsViewAdapter(list)
        adsViewPager.currentItem = 1
        adsViewPager.clipToPadding = false
        adsViewPager.clipChildren = false
        adsViewPager.offscreenPageLimit = 3
        adsViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        if (unitProvider.getLang()) {
            imgLang.setImageResource(R.drawable.ic_rus)
        } else {
            imgLang.setImageResource(R.drawable.ic_uzb)
        }

        val compositePagerTransformer = CompositePageTransformer()
        compositePagerTransformer.addTransformer(MarginPageTransformer(20))
        compositePagerTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
            val r: Float = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.25f
        })

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
            if (unitProvider.getLang()) {
                ussdCall(UssdCodes.balanceUssdRu, it.context)
            } else ussdCall(UssdCodes.balanceUssdUz, it.context)
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

    private fun bindToast(isLoaded: Boolean) {
        activity?.runOnUiThread {
            if (isLoaded) {
                Toast.makeText(requireContext(), getString(R.string.text_data_loaded), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                        requireContext(),
                        "Internet ulanishida nosozlik bor",
                        Toast.LENGTH_LONG
                ).show()
            }
        }
        dialog.dismiss()
    }

//    private fun updateConfig(lang: Boolean) {
//        val dLocale = if (lang) {
//            Locale("uz", "UZ")
//        } else Locale("ru", "RU")
//        val dm = resources.displayMetrics
//        val conf = resources.configuration
//        conf.locale = dLocale
//        resources.updateConfiguration(conf, dm)
//    }


    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }
}