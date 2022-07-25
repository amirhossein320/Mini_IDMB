package ir.interview.idmb

import android.app.Application
import ir.interview.idmb.di.Container

class App : Application() {

    val injection = Container(this)
}