package com.example.setting.model

/**
 * @author create by Linqy
 * @time 2025-06-18 : 15:55
 * function:
 */
sealed class PowerEvent {
    //功率
    data object POWER_CHECK : PowerEvent()
    data object POWER_FIND : PowerEvent()
}