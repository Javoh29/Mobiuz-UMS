package com.range.mobiuz.ui.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.skydoves.elasticviews.ElasticButton
import kotlinx.android.synthetic.main.fragment_single.*
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import com.range.mobiuz.R
import com.range.mobiuz.data.db.entity.MinutesModel
import com.range.mobiuz.data.db.entity.PacketModel
import com.range.mobiuz.data.repository.MobiuzRepository
import com.range.mobiuz.ui.adapter.MinutesAdapter
import com.range.mobiuz.ui.adapter.PacketsAdapter
import com.range.mobiuz.ui.base.ScopedFragment
import com.range.mobiuz.utils.UssdCodes
import com.range.mobiuz.utils.lazyDeferred
import com.range.mobiuz.utils.ussdCall

class SingleFragment(private val index: Int, private val isSMS: Boolean) : ScopedFragment(R.layout.fragment_single), SingleAction {

    private val mobiuzRepository: MobiuzRepository by instance<MobiuzRepository>()

    private var dialog: Dialog? = null
    private var btnOk: ElasticButton? = null
    private var dealerCode: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        avi.show()
        recyclerSingle.layoutManager = LinearLayoutManager(context)
        dialog = Dialog(requireContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setContentView(R.layout.dialog_ask)

        dialog?.findViewById<AppCompatTextView>(R.id.tvAsk)?.text = getString(R.string.confirm_service_ask)
        val btnCancel: ElasticButton = dialog?.findViewById(R.id.btnCancel)!!
        btnOk = dialog?.findViewById(R.id.btnOk)!!

        btnCancel.setOnClickListener { dialog?.dismiss() }

        if (!isSMS){
            if (index == 2 || index == 4){
                btnCheck.visibility = View.GONE
            }
        }

        btnCheck.setOnClickListener {
            if (isSMS){
                if (index == 0){
                    ussdCall(UssdCodes.minuteCheck, it.context)
                }else ussdCall(UssdCodes.smsCheck, it.context)
            }else{
                when(index){
                    0 -> ussdCall(UssdCodes.packageCheck, it.context)
                    1 -> ussdCall(UssdCodes.nightCheck, it.context)
                    3 -> ussdCall(UssdCodes.miniCheck, it.context)
                }
            }
        }

        loadData()
        loadCode()
    }

    private fun loadData() = launch {
        if (isSMS) {
            lazyDeferred { mobiuzRepository.getMinutes() }.value.await().observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer
                bindMinutesUI(it)
            })
        } else {
            lazyDeferred { mobiuzRepository.getPackets() }.value.await().observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer
                bindPacketsUI(it)
            })
        }
    }

    private fun loadCode() = launch {
        lazyDeferred { mobiuzRepository.getDealerCode() }.value.await().observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            dealerCode = it.code
        })
    }

    private fun bindPacketsUI(list: List<PacketModel>) {
        val model: ArrayList<PacketModel> = ArrayList()
        list.forEach {
            if (it.type == index) {
                model.add(it)
            }
        }
        recyclerSingle.adapter = PacketsAdapter(model, this)
        recyclerSingle.visibility = View.VISIBLE
        avi.hide()
    }

    private fun bindMinutesUI(list: List<MinutesModel>) {
        val model: ArrayList<MinutesModel> = ArrayList()
        list.forEach {
            if (it.type == index) {
                model.add(it)
            }
        }
        recyclerSingle.adapter = MinutesAdapter(model, this)
        recyclerSingle.visibility = View.VISIBLE
        avi.hide()
    }

    override fun itemClick(code: String) {
        if (dealerCode != null) {
            dialog?.show()
            btnOk?.setOnClickListener {
                if (isSMS){
                    if (index == 1){
                        ussdCall(code, it.context)
                    }else{
                        val ussd = UssdCodes.netPackets + code + "*1" + dealerCode + UssdCodes.encodedHash
                        ussdCall(ussd, it.context)
                    }
                }else {
                    val ussd = UssdCodes.netPackets + code + dealerCode + UssdCodes.encodedHash
                    ussdCall(ussd, it.context)
                }
                dialog?.dismiss()
            }
        } else loadCode()
    }
}

interface SingleAction {
    fun itemClick(code: String)
}