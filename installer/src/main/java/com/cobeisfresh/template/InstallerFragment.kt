package com.cobeisfresh.template

import androidx.core.net.toUri
import androidx.navigation.Navigation
import com.cartenz.component_base.extensions.onClick
import kotlinx.android.synthetic.main.fragment_installer.*

class InstallerFragment : com.cartenz.component_base.BaseFragment() {
    override fun getLayout() = R.layout.fragment_installer

    override fun viewReady() {

        btn_weather.onClick {
            val uri = getString(R.string.feature_weather_deeplink).toUri()
            Navigation.findNavController(it).navigate(uri)
        }

        btn_test.onClick {
            val uri = getString(R.string.feature_test_deeplink).toUri()
            Navigation.findNavController(it).navigate(uri)
        }
    }

}