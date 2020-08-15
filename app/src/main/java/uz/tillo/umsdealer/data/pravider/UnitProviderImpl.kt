package uz.tillo.umsdealer.data.pravider

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import java.net.InetAddress
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.*

class UnitProviderImpl(private val context: Context) : UnitProvider, PreferenceProvider(context) {

    private val saveDate = "saveDate"

    override suspend fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isConnected) {
            try {
                !InetAddress.getByName("google.com").equals("")
            }catch (e: UnknownHostException){
                false
            }
        }else false
    }

    @SuppressLint("SimpleDateFormat")
    override fun saveLoadDate() {
        preferences.edit().putString(saveDate, SimpleDateFormat("dd.MM.yy").format(Date())).apply()
    }

    override fun getSaveDate(): String {
        return preferences.getString(saveDate, "not")!!
    }

}