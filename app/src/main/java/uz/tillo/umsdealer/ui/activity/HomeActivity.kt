package uz.tillo.umsdealer.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import uz.tillo.umsdealer.R

class HomeActivity : AppCompatActivity() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_home)
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onBackPressed() {
        if (!navController!!.popBackStack())
            super.onBackPressed()
    }
}