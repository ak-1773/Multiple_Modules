package com.example.lib_base

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.therouter.TheRouter

/**
 * @author create by Linqy
 * @time 2025-06-10 : 11:37
 * function: Activity 基类
 */
abstract class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TheRouter.init(this)
    }

    open fun onBack() {
        finish()
    }
}