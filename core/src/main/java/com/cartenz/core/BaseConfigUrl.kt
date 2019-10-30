package com.cartenz.core

import android.content.Context
import android.text.TextUtils

object BaseConfigUrl {
    var URLTARGET = "URLTARGET"
    val BASIC_URL = "http://www.google.com"

    fun setBaseUrl(url: String) {
        PrefManager.saveToPref(CartenzApp.instance, URLTARGET, url)
    }

    fun getBaseUrl(): String? {
        val context = CartenzApp.instance
        var urlTarget = PrefManager.getPref(context, URLTARGET)
        if (TextUtils.isEmpty(urlTarget)) {
            PrefManager.saveToPref(context, URLTARGET, BASIC_URL)
            urlTarget = BASIC_URL
        }
        val lastString = urlTarget.substring(urlTarget.length - 1)
        if (lastString != "/") {
            urlTarget += "/"
        }
        return urlTarget
    }


}

