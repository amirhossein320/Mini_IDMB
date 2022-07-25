package ir.interview.idmb.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ir.interview.idmb.ui.MainActivity


open class BaseFragment<T : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    private var _binding: T? = null
    open val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    open fun navigateTo(fragment: Fragment) {
        (requireActivity() as MainActivity).navigateTo(fragment)
    }

    open fun navigateToChild(fragment: Fragment, container: Int) {
        childFragmentManager.beginTransaction().replace(container, fragment)
            .addToBackStack(fragment.javaClass.canonicalName).commit()
    }

    open fun popBack() {
        parentFragment?.let {
            if (it.childFragmentManager.backStackEntryCount > 0)
                it.childFragmentManager.popBackStack()
        } ?: (requireActivity() as MainActivity).popBack()
    }

    open fun getInjection() = (requireActivity() as MainActivity).getInjection()

}