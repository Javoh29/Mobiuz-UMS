package com.range.mobiuz.ui.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.skydoves.elasticviews.ElasticButton
import kotlinx.android.synthetic.main.fragment_rate.*
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import com.range.mobiuz.R
import com.range.mobiuz.data.db.entity.RateModel
import com.range.mobiuz.data.repository.MobiuzRepository
import com.range.mobiuz.ui.adapter.RateAdapter
import com.range.mobiuz.ui.base.ScopedFragment
import com.range.mobiuz.utils.UssdCodes
import com.range.mobiuz.utils.lazyDeferred
import com.range.mobiuz.utils.ussdCall

class RateFragment : ScopedFragment(R.layout.fragment_rate), RateAction {

    private val mobiuzRepository: MobiuzRepository by instance<MobiuzRepository>()

    private var dialog: Dialog? = null
    private var btnOk: ElasticButton? = null
    private var dealerCode: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerRate.layoutManager = LinearLayoutManager(context)

        dialog = Dialog(requireContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setContentView(R.layout.dialog_ask)

        dialog?.findViewById<AppCompatTextView>(R.id.tvAsk)?.text = getString(R.string.confirm_ask)
        val btnCancel: ElasticButton = dialog?.findViewById(R.id.btnCancel)!!
        btnOk = dialog?.findViewById(R.id.btnOk)!!

        btnCancel.setOnClickListener { dialog?.dismiss() }

        imgBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        avi.show()
        loadData()
        loadCode()
    }

    private fun loadData() = launch {
        lazyDeferred { mobiuzRepository.getRate() }.value.await().observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            bindUi(it)
        })
    }

    private fun loadCode() = launch {
        lazyDeferred { mobiuzRepository.getDealerCode() }.value.await().observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            dealerCode = it.code
        })
    }

    private fun bindUi(list: List<RateModel>){
        recyclerRate.adapter = RateAdapter(list, this)
        avi.hide()
    }

    override fun itemClick(code: String) {
        if (dealerCode != null) {
            dialog?.show()
            btnOk?.setOnClickListener {
                val ussd = UssdCodes.netPackets + code + dealerCode + UssdCodes.encodedHash
                ussdCall(ussd, it.context)
                dialog?.dismiss()
            }
        } else loadCode()
    }

    override fun getLang(): Boolean {
        return unitProvider.getLang()
    }
}

interface RateAction{

    fun itemClick(code: String)

    fun getLang(): Boolean

}