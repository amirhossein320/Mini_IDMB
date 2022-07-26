package ir.interview.idmb.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.view.View
import android.widget.Toast

fun Context.hasNetwork(): Boolean {
    val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        connMgr.activeNetwork?.let { network ->
            connMgr.getNetworkCapabilities(network)?.let { capabilities ->
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            } ?: false
        } ?: false
    } else {
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        networkInfo?.run { isConnected == true } ?: false
    }
}

inline fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}