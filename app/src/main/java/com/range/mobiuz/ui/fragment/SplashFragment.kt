package com.range.mobiuz.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import com.range.mobiuz.R
import com.range.mobiuz.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.fragment_splash.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : ScopedFragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvSplash.visibility = View.VISIBLE
        tvSplash.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.from_top))
        imgSplash.visibility = View.VISIBLE
        imgSplash.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.from_bottom))

        launch {
            delay(2000)
            Navigation.findNavController(view).navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }
    }

}