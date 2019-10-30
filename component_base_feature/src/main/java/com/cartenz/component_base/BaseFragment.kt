package com.cartenz.component_base

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.annotation.StringRes
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onCreateReady()
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // This callback will only be called when MyFragment is at least Started.
        viewReady()
    }

    fun navUpHandler(navUpInterface: navUpInterface) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navUpInterface.navUpHandler()
            }
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    open fun onCreateReady(){

    }

    open fun navUpDeeplink(destination: String) {
        val uri = destination.toUri()
        Navigation.findNavController(view!!).navigate(uri)
    }

    abstract fun viewReady()

    abstract fun getLayout(): Int

    open fun showError(@StringRes errorMessage: Int, rootView: View) {
        (activity as BaseActivity).showError(errorMessage, rootView)
    }

    open fun showError(errorMessage: String?, rootView: View) {
        (activity as BaseActivity).showError(errorMessage, rootView)
    }

    open fun showLoading(progressBar: ProgressBar) {
        (activity as BaseActivity).showLoading(progressBar)
    }

    open fun hideLoading(progressBar: ProgressBar) {
        (activity as BaseActivity).hideLoading(progressBar)
    }


}