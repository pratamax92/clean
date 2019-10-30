package com.cartenz.feature_test

import android.util.Log
import androidx.core.net.toUri
import androidx.navigation.Navigation
import com.cartenz.component_base.BaseFragment
import com.cartenz.component_base.extensions.onClick
import com.cartenz.component_base.navUpInterface
import kotlinx.android.synthetic.main.fragment_test.*


class TestFragment : BaseFragment(), navUpInterface {
    override fun getLayout() = R.layout.fragment_test

    override fun viewReady() {
        btn_test.onClick {
            val uri = getString(R.string.feature_weather_deeplink).toUri()
            Navigation.findNavController(it).navigate(uri)
        }

        navUpHandler(this)
    }

    override fun navUpHandler() {
        if(!Navigation.findNavController(view!!).navigateUp()){
            navUpDeeplink(getString(R.string.installer_app))
        }
    }


}