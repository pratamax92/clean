package com.cartenz.feature_weather.ui.weatherMenu

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.cartenz.component_base.extensions.hideKeyboard
import com.cartenz.component_base.extensions.onClick
import com.cartenz.component_base.extensions.snackbar
import com.cartenz.component_base.extensions.subscribe
import com.cartenz.feature_weather.R
import com.cartenz.feature_weather.TestActivity
import com.cartenz.feature_weather.common.convertKelvinToCelsius
import com.example.domain.data.WeatherInfo
import kotlinx.android.synthetic.main.fragment_weather_menu.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherMenuFragment : com.cartenz.component_base.BaseFragment() {
    private val menuViewModel: WeatherMenuViewModel by viewModel()

    override fun getLayout() = R.layout.fragment_weather_menu


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.add(getString(R.string.app_name))
            .setTitle(getString(R.string.app_name))
            .setIcon(R.drawable.ic_android_black_24dp)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val iid = item!!.itemId
        if (iid != android.R.id.home) {
            TestActivity.startThisActivityFromFragment(this)
            return false
        } else {
            return super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateReady() {
        super.onCreateReady()
        setHasOptionsMenu(true);
    }

    override fun viewReady() {
        subscribeData()

        getWeather.onClick {
            weatherFragmentContainer.hideKeyboard()
            menuViewModel.getWeatherForLocation(cityInput.text.toString())
        }

        checkWeatherDb.onClick {
            weatherFragmentContainer.hideKeyboard()
            menuViewModel.getWeatherFromDatabase(cityInput.text.toString())
        }

        showWeatherDetails.onClick {
            val bundle = bundleOf(
                "var1" to "test",
                "var2" to "tost"
            )
            Navigation.findNavController(it)
                .navigate(R.id.action_menuFragment_to_DetailFragment, bundle)
        }
    }

    override fun subscribeData() {
        menuViewModel.weatherState.subscribe(this, ::handleViewState)
        menuViewModel.checkDbExist.subscribe(this, ::handleCheckExist)
    }


    private fun handleViewState(viewState: com.cartenz.component_base.ViewState<WeatherInfo>) {
        when (viewState) {
            is com.cartenz.component_base.Loading -> showLoading(weatherLoadingProgress)
            is com.cartenz.component_base.Success -> showWeatherData(viewState.data)
            is com.cartenz.component_base.Error -> handleError(viewState.error.localizedMessage)
            is com.cartenz.component_base.NoInternetState -> showNoInternetError()
        }
    }

    private fun handleCheckExist(boolean: Boolean) {
        if (boolean) {
            snackbar("EXIST", weatherFragmentContainer)
        } else {
            snackbar("NOT EXIST", weatherFragmentContainer)
        }
    }


    private fun handleError(error: String) {
        hideLoading(weatherLoadingProgress)
        showError(error, weatherFragmentContainer)
    }

    private fun showNoInternetError() {
        hideLoading(weatherLoadingProgress)
        snackbar(getString(R.string.no_internet_error_message), weatherFragmentContainer)
    }

    private fun showWeatherData(weatherInfo: WeatherInfo) {
        hideLoading(weatherLoadingProgress)
        temperature.text = convertKelvinToCelsius(weatherInfo.temperature)
        pressure.text = weatherInfo.pressure.toString()
        humidity.text = weatherInfo.humidity.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

}

