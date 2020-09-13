package com.range.mobiuz.ui.activity

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.range.mobiuz.R
import com.range.mobiuz.utils.ContextWrap
import java.util.*

class HomeActivity : AppCompatActivity() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_home)
        navController = findNavController(R.id.nav_host_fragment)
        requestPermission()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_SYNC_SETTINGS,
                        Manifest.permission.REQUEST_INSTALL_PACKAGES,
                        Manifest.permission.READ_PHONE_STATE
                ),
                1
        )
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