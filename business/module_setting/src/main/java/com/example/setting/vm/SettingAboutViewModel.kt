package com.example.setting.vm

import com.example.kv.KVManager
import com.example.kv.key.KeySet
import com.example.lib_base.BaseViewModel
import com.example.lib_base.toast.XToast
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingAboutViewModel @Inject constructor(
    private val xt: XToast,
) : BaseViewModel() {
    private var clickCount = 0
    private var lastClickTime = 0L
    fun processClick() {
        val now = System.currentTimeMillis()
        val timeoutMillis = 600L
        if (now - lastClickTime > timeoutMillis) {
            clickCount = 0 // 超时重置
        }

        clickCount++
        lastClickTime = now

        val requiredClicks = 20
        if (clickCount >= requiredClicks) {
            if (!KVManager.instance.getBoolean(KeySet.KEY_DEBUG, false)) {
                KVManager.instance.put(KeySet.KEY_DEBUG, true)
                clickCount = 0
                xt.toast("开启调试模式成功")
            } else {
                xt.toast("调试模式已开启")
            }
        }
    }
}