package ir.interview.idmb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ir.interview.idmb.App
import ir.interview.idmb.R
import ir.interview.idmb.databinding.ActivityMainBinding
import ir.interview.idmb.ui.movies.MovieFragment
import ir.interview.idmb.utils.gone
import ir.interview.idmb.utils.visible

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val toastTimer : CountDownTimer by lazy {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                binding.txtNoInternet.gone()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToWithoutStack(MovieFragment())
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) popBack()
        else {
            if (supportFragmentManager.fragments.last().childFragmentManager.backStackEntryCount >= 1) {
                supportFragmentManager.fragments.last().childFragmentManager.popBackStack()
            } else {
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

    fun popBack() {
        supportFragmentManager.popBackStack()
    }

    fun getInjection() = (application as App).injection

    fun showNoInternet() {
        toastTimer.cancel()
        toastTimer.start()
        binding.txtNoInternet.visible()
    }
}