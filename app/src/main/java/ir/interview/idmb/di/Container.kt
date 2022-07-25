package ir.interview.idmb.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import ir.interview.idmb.BuildConfig
import ir.interview.idmb.data.database.AppDatabase
import ir.interview.idmb.data.network.ApiService
import ir.interview.idmb.data.network.OkHttpInterceptors
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Container(context: Context) {

    private val interceptors = OkHttpInterceptors()

    private val okHttpClient = if (BuildConfig.DEBUG) {
        OkHttpClient.Builder()
            .addInterceptor(interceptors.loggingInterceptor())
            .addInterceptor(interceptors.baseParamInterceptor())
            .addInterceptor(interceptors.networkInterceptor(context))
            .build()
    } else {
        OkHttpClient.Builder()
            .addInterceptor(interceptors.baseParamInterceptor())
            .addInterceptor(interceptors.networkInterceptor(context))
            .build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    private val database =  Room.databaseBuilder(
        context, AppDatabase::class.java, "database.db"
    ).build()

}