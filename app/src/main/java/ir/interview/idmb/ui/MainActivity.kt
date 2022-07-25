package ir.interview.idmb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ir.interview.idmb.App
import ir.interview.idmb.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) popBack()
        else {
            if (supportFragmentManager.fragments.last().childFragmentManager.backStackEntryCount >= 1){
                supportFragmentManager.fragments.last().childFragmentManager.popBackStack()
            }else{
                super.onBackPressed()
            }
        }
    }

    fun navigateTo(fragment: Fragment, container: Int = R.id.mainContainer) {
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .addToBackStack(fragment.javaClass.canonicalName)
            .commit()
    }

    fun navigateToWithoutStack(fragment: Fragment, container: Int = R.id.mainContainer) {
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commit()
    }

    fun popBack() { supportFragmentManager.popBackStack() }

    fun getInjection() = (application as App).injection
}