package com.example.setting.vm

import com.example.common.utils.StringUtils
import com.example.kv.KVManager
import com.example.kv.key.KeySet
import com.example.lib_base.BaseViewModel
import com.example.lib_base.toast.XToast
import com.example.setting.model.ServerEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
@OptIn(kotlinx.coroutines.FlowPreview::class)
class SettingServerViewModel @Inject constructor(
    val xt: XToast,
) : BaseViewModel() {

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    // 状态流暴露给View层
    private val _uiState = MutableStateFlow<Map<ServerEvent, String>>(emptyMap())
    val uiState: StateFlow<Map<ServerEvent, String>> = _uiState.asStateFlow()

    /**
     *加载服务器设置数据
     */
    fun loadServerData() {
        val ip = KVManager.instance.getString(KeySet.KEY_IP, "192.168.1.1")
        val port = KVManager.instance.getString(KeySet.KEY_PORT, "8080")
        _uiState.value = mapOf(
            ServerEvent.SERVER_IP to ip,
            ServerEvent.SERVER_PORT to port
        ).toMutableMap()
    }

    private fun showDialog() {
        _showDialog.value = true
    }

    fun hideDialog() {
        _showDialog.value = false
    }

    /**
     * 更新服务器信息
     */
    fun updateServerInfo(event: ServerEvent, value: String) {
        _uiState.value = _uiState.value.toMutableMap().apply {
            this[event] = value
        }
    }

    fun checkServerInfo(ip: String, port: String): Boolean {
        if (!StringUtils.isIpAddress(ip)) {
            xt.toast("请输入正确的IP地址")
            return false
        }
        if (!StringUtils.isPort(port)) {
            xt.toast("请输入正确的端口号")
            return false
        }
        return true
    }

    /**
     * 保存服务器信息
     */
    fun saveServerInfo(ip: String, port: String) {
        KVManager.instance.put(KeySet.KEY_IP, ip)
        KVManager.instance.put(KeySet.KEY_PORT, port)
    }

}