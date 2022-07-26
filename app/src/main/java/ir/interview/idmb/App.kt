package ir.interview.idmb

import android.app.Application
import ir.interview.idmb.di.Container

class App : Application() {

    lateinit var injection: Container

    override fun onCreate() {
        super.onCreate()

         injection = Container(this)
    }
}