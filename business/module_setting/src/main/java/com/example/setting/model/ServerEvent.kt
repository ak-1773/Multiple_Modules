package com.example.setting.model

/**
 * @author create by Linqy
 * @time 2025-06-18 : 15:55
 * function:
 */
sealed class ServerEvent {
    //获取服务器信息
    data object SERVER_IP : ServerEvent()
    data object SERVER_PORT : ServerEvent()
}