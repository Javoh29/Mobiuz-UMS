package uz.tillo.umsdealer.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import uz.tillo.umsdealer.R
import uz.tillo.umsdealer.data.repository.MobiuzRepository
import uz.tillo.umsdealer.ui.base.ScopedFragment
import uz.tillo.umsdealer.utils.lazyDeferred

class SingleFragment(private val index: Int, private val isSMS: Boolean) : ScopedFragment(R.layout.fragment_single) {

    private val mobiuzRepository: MobiuzRepository by instance<MobiuzRepository>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
    }

    private fun loadData() = launch {
        lazyDeferred { mobiuzRepository.getPackets() }.value.await().observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            bindUI()
        })
    }

    private fun bindUI(){

    }
}