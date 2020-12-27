package com.range.mobiuz.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.range.mobiuz.App
import com.range.mobiuz.R
import com.range.mobiuz.data.pravider.UnitProvider
import com.range.mobiuz.data.repository.MobiuzRepository
import com.range.mobiuz.utils.ContextWrap
import com.range.mobiuz.utils.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import org.kodein.di.KodeinAware
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val unitProvider: UnitProvider by instance()
    private val mobiuzRepository: MobiuzRepository by instance()
    private var navController: NavController? = null
    private var simpleDate = SimpleDateFormat("dd.MM.yy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_home)
        navController = findNavController(R.id.nav_host_fragment)
        loadData()
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            if (unitProvider.getSaveDate() != simpleDate.format(Date())){
                if (unitProvider.isOnline()) {
                    bindToast(lazyDeferred { mobiuzRepository.fetchingAllData() }.value.await())
                }
                App.isLoaded = false
            }
        }
    }


    private fun bindToast(isLoaded: Boolean) {
        runOnUiThread {
            if (isLoaded) {
                Toast.makeText(this, getString(R.string.text_data_loaded), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                        this,
                        getString(R.string.text_data_loaded_err),
                        Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val locale = if (PreferenceManager.getDefaultSharedPreferences(newBase).getBoolean("LANGUAGE", false)) {
            Locale("uz", "UZ")
        } else {
            Locale("ru", "RU")
        }
        val context: Context = ContextWrap.wrap(newBase, locale)
        super.attachBaseContext(context)
    }

    override fun onBackPressed() {
        if (!navController!!.popBackStack())
            super.onBackPressed()
    }
}