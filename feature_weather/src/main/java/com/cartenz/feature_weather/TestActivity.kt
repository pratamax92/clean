package com.cartenz.feature_weather

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.cartenz.component_base.BaseActivity
import com.cartenz.component_base.extensions.onClick
import kotlinx.android.synthetic.main.activity_test.*


open class TestActivity : BaseActivity() {
    override fun initBeforeCreateContent() {

    }

    override fun getLayoutId() = R.layout.activity_test

    override fun initBaseCreate() {
        btn_test.onClick {
            result()
        }
    }

    override fun initDestroy() {

    }


    private fun result() {
        val intent = Intent()
        intent.putExtra(DATARESULT, "kambing")
        this@TestActivity.setResult(Activity.RESULT_OK, intent)
        this@TestActivity.finish()
    }


    companion object {
        val DATARESULT = "DATARESULT"
        val TESTID = 789

        fun startThisActivityFromFragment(fragment: Fragment) {
            val intent = Intent(fragment.context, TestActivity::class.java)
            fragment.startActivityForResult(intent, TestActivity.TESTID)
        }
    }


}
