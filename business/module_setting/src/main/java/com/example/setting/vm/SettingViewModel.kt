package com.example.setting.vm

import androidx.lifecycle.viewModelScope
import com.example.kv.KVManager
import com.example.kv.key.KeySet
import com.example.lib_base.BaseViewModel
import com.example.lib_base.config.DurationConfig
import com.example.setting.model.ClickEvent
import com.therouter.TheRouter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(kotlinx.coroutines.FlowPreview::class)
class SettingViewModel : BaseViewModel() {
    private val _clickEvents = MutableSharedFlow<ClickEvent>(replay = 1)

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    init {
        // 监听点击事件
        _clickEvents
            // 300ms 的防抖时间
            .debounce(DurationConfig.DURATION_ANTI_SHAKE)
            .onEach { clickEvent ->
                when (clickEvent) {
                    ClickEvent.RFID -> {
                        TheRouter.build("/setting/SettingPowerActivity")
                            .navigation()
                    }

                    ClickEvent.SERVER -> {
                        TheRouter.build("/setting/SettingServiceActivity")
                            .navigation()
                    }

                    ClickEvent.ABOUT -> {
                        TheRouter.build("/setting/SystemAboutActivity")
                            .navigation()
                    }

                    ClickEvent.MODULE -> {
                        _showDialog.value = true
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun onClick(clickEvent: ClickEvent) {
        _clickEvents.tryEmit(clickEvent)
    }

    fun hideDialog() {
        _showDialog.value = false
    }

    fun saveModule(module: String) {
        KVManager.instance.put(KeySet.KEY_MODULE, module)
    }
}