package com.example.module_home.model

/**
 * @author create by Linqy
 * @time 2025-06-18 : 11:01
 * function:
 */
sealed class ClickEvent {
    data object HELLO : ClickEvent()
    data object TEST : ClickEvent()
    data object SETTING : ClickEvent()
}