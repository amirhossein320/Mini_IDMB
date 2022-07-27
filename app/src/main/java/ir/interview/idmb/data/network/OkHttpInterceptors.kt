package ir.interview.idmb.data.network

import android.content.Context
import android.util.Log
import ir.interview.idmb.BuildConfig
import ir.interview.idmb.R
import ir.interview.idmb.utils.hasNetwork
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import java.net.SocketTimeoutException


class OkHttpInterceptors {

    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun baseParamInterceptor(): Interceptor = Interceptor {
        val originalRequest = it.request()

        val url = originalRequest.url.newBuilder()
            .addQueryParameter("apikey", BuildConfig.API_KEY)
            .build()
        val newRequest = originalRequest.newBuilder()
            .url(url).build()
        it.proceed(newRequest)
    }


    fun networkInterceptor(context: Context): Interceptor = Interceptor {

        val originalRequest = it.request()
        try {
            if (context.hasNetwork()) {
               return@Interceptor it.proceed(originalRequest)
            } else {
                throw NoConnectivityException(context.getString(R.string.err_no_internet))
            }
        } catch (e: SocketTimeoutException) {
            throw SocketTimeoutException(context.getString(R.string.err_time_out))
        }

    }
}

class NoConnectivityException(message: String) : IOException(message)