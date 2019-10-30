package com.cartenz.component_base


import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.cartenz.component_base.extensions.gone
import com.cartenz.component_base.extensions.snackbar
import com.cartenz.component_base.extensions.visible


abstract class BaseActivity : AppCompatActivity() {
    val EMPTY_STRING = ""

    //updateStatusBarTextColor
    val STATUSBAR_TEXT_COLOR_DARK: Int = 1
    val STATUSBAR_TEXT_COLOR_LIGHT: Int = 0


    lateinit var intentData: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBeforeCreateContent()
        setContentView(getLayoutId())
        intentData = this.getIntent();
//        setTransparentStatusBarColor(Color.TRANSPARENT)
//        updateStatusBarTextColor(STATUSBAR_TEXT_COLOR_LIGHT)
        initBaseCreate()
    }

    fun requestFullscreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    fun setTransparentStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = color
        }
    }


    fun updateStatusBarTextColor(type: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor = window.decorView
            if (type == STATUSBAR_TEXT_COLOR_DARK) {
                decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decor.setSystemUiVisibility(0);
            }
        }
    }


    protected abstract fun initBeforeCreateContent()

    protected abstract fun getLayoutId(): Int

    protected abstract fun initBaseCreate()

    protected abstract fun initDestroy()

    override fun onDestroy() {
        initDestroy()
        super.onDestroy()
    }


    fun showError(@StringRes errorMessage: Int, rootView: View) = snackbar(errorMessage, rootView)

    fun showError(errorMessage: String?, rootView: View) =
        snackbar(errorMessage ?: EMPTY_STRING, rootView)

    fun showLoading(progressBar: ProgressBar) = progressBar.visible()

    fun hideLoading(progressBar: ProgressBar) = progressBar.gone()

}

