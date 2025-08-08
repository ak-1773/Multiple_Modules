package com.example.lib_base.toast

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * @author create by Linqy
 * @time 2024-09-13 : 19:53
 * function:吐司
 */


class XToast @Inject constructor(
    @ApplicationContext val context: Context,
) {
    private var toast: Toast? = null


    fun toast(msg: String) {
        toast?.cancel()
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast?.show()
    }

    fun toast(@StringRes resId: Int) {
        toast?.cancel()
        toast = Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT)
        toast?.show()
    }
}