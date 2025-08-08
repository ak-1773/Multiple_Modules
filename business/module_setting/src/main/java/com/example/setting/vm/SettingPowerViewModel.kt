package com.example.setting.vm

import com.example.kv.KVManager
import com.example.kv.key.KeySet
import com.example.lib_base.BaseViewModel
import com.example.lib_base.config.DefaultValueConfig
import com.example.setting.model.PowerEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@OptIn(kotlinx.coroutines.FlowPreview::class)
class SettingPowerViewModel : BaseViewModel() {
    private val _showDialog = MutableStateFlow<PowerEvent?>(null)
    val showDialog: StateFlow<PowerEvent?> = _showDialog.asStateFlow()

    // 状态流暴露给View层
    private val _uiState = MutableStateFlow<Map<PowerEvent, Int>>(emptyMap())
    val uiState: StateFlow<Map<PowerEvent, Int>> = _uiState.asStateFlow()

    fun showDialog(powerEvent: PowerEvent) {
        _showDialog.value = powerEvent
    }

    fun hideDialog() {
        _showDialog.value = null
    }

    /**
     * 保存功率
     */
    fun savePower(powerEvent: PowerEvent, powerValue: Int) {
        when (powerEvent) {
            PowerEvent.POWER_CHECK -> {
                KVManager.instance.put(KeySet.KEY_POWER_CHECK, powerValue)
            }

            PowerEvent.POWER_FIND -> {
                KVManager.instance.put(KeySet.KEY_POWER_FIND, powerValue)
            }
        }
    }
}