package com.example.setting.model

/**
 * @author create by Linqy
 * @time 2025-06-18 : 11:56
 * function:
 */
sealed class ClickEvent {
    data object MODULE : ClickEvent()
    data object RFID : ClickEvent()
    data object SERVER : ClickEvent()
    data object ABOUT : ClickEvent()
}