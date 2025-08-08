package com.example.article.application

import android.content.Context
import androidx.multidex.MultiDex
import com.example.lib_base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * @author create by Linqy
 * @time 2025-06-10 : 14:28
 * function:
 */
@HiltAndroidApp
class ExampleApplication : BaseApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}