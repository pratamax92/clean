package com.cartenz.feature_weather.ui.welcome

import android.util.Log
import androidx.navigation.Navigation
import com.cartenz.feature_weather.R
import com.cartenz.component_base.BaseFragment
import com.cartenz.component_base.extensions.onClick
import com.cartenz.component_base.navUpInterface
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : com.cartenz.component_base.BaseFragment(), navUpInterface {
    override fun getLayout() = R.layout.fragment_welcome

    override fun viewReady() {
        nextScreen.onClick {
            Navigation.findNavController(it).navigate(R.id.action_welcomeFragment_to_menuFragment)
        }
        navUpHandler(this)
    }

    override fun navUpHandler() {
        if(!Navigation.findNavController(view!!).navigateUp()){
            navUpDeeplink(getString(R.string.installer_app))
        }
    }



}