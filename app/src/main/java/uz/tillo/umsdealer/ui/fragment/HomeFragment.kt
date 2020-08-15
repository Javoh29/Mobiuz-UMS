package uz.tillo.umsdealer.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import uz.tillo.umsdealer.R
import uz.tillo.umsdealer.data.repository.MobiuzRepository
import uz.tillo.umsdealer.ui.adapter.AdsViewAdapter
import uz.tillo.umsdealer.ui.base.ScopedFragment
import uz.tillo.umsdealer.utils.DownloadDialog
import uz.tillo.umsdealer.utils.lazyDeferred
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : ScopedFragment(R.layout.fragment_home) {

    private val mobiuzRepository: MobiuzRepository by instance<MobiuzRepository>()
    private var timer: TimerTask? = null
    private val dialog = DownloadDialog()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
        loadData()
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadData() = launch(Dispatchers.IO){
        if (unitProvider.getSaveDate() != SimpleDateFormat("dd.MM.yy").format(Date())){
            if (unitProvider.isOnline()){
                bindToast(lazyDeferred { mobiuzRepository.fetchingAllData() }.value.await())
            }else dialog.dismiss()
        }else dialog.dismiss()
    }

    private fun bindUI(){
        dialog.isCancelable = false
        dialog.show(activity?.supportFragmentManager!!, "DownloadDialog")
        adsViewPager.adapter = AdsViewAdapter()
        adsViewPager.currentItem = 1
        adsViewPager.clipToPadding = false
        adsViewPager.clipChildren = false
        adsViewPager.offscreenPageLimit = 3
        adsViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePagerTransformer = CompositePageTransformer()
        compositePagerTransformer.addTransformer(MarginPageTransformer(20))
        compositePagerTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
            val r: Float = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.25f
        })

        adsViewPager.setPageTransformer(compositePagerTransformer)

        var page = 1

        timer = object : TimerTask(){
            override fun run() {
                requireActivity().runOnUiThread {
                    adsViewPager.currentItem = page % 5
                }
                page++
            }
        }
        Timer().schedule(timer, 0, 5000)


        cardInternet.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToInternetFragment())
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

    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }
}