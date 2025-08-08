package com.example.lib_base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @author create by Linqy
 * @time 2025-06-10 : 11:39
 * function:
 */
abstract class BaseViewModel : ViewModel() {
    var job: Job? = null

    //是否激活读取器
    val _isActivateReader = MutableStateFlow(false)
    val isActivateReader: StateFlow<Boolean> = _isActivateReader.asStateFlow()

    /**
     * 取消协程
     */
    fun cancelCoroutine() {
        if (job?.isActive == true) {
            job?.cancel()
        }
    }
}