package uz.tillo.umsdealer.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_internet.*
import uz.tillo.umsdealer.R
import uz.tillo.umsdealer.ui.adapter.FragmentAdapter

class InternetFragment : Fragment(R.layout.fragment_internet) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    private fun bindUI(){
        val adapter = FragmentAdapter(childFragmentManager)
        adapter.addFragment(SingleFragment(1, false), "Oylik paketlar")
        adapter.addFragment(SingleFragment(1, false), "Tungi paketlar")
        adapter.addFragment(SingleFragment(1, false), "Tungi Drive")
        viewPagerNet.adapter = adapter

        imgBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }
}