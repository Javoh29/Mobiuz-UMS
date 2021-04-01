package com.range.mobiuz.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.range.mobiuz.R
import com.range.mobiuz.data.db.entity.UssdCodeModel
import com.range.mobiuz.data.repository.MobiuzRepository
import com.range.mobiuz.ui.adapter.UssdAdapter
import com.range.mobiuz.ui.base.ScopedFragment
import com.range.mobiuz.utils.lazyDeferred
import kotlinx.android.synthetic.main.fragment_ussd_codes.*
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class UssdCodesFragment : ScopedFragment(R.layout.fragment_ussd_codes) {

    private val mobiuzRepository: MobiuzRepository by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
        avi.show()
        loadData()
    }

    private fun loadData() = launch {
        lazyDeferred { mobiuzRepository.getUssdCodes() }.value.await().observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            if (it.isEmpty()) return@Observer
            bindUI(it)
        })
    }

    private fun bindUI(list: List<UssdCodeModel>) {
        recyclerUssd.adapter = UssdAdapter(list, unitProvider.getLang())
        avi.hide()
    }

}