package com.example.lib_sound.inversion

/**
 * @author create by Linqy
 * @time 2024-10-14 : 9:52
 * function:声音接口, 控制反转
 */
interface ISound {
    /**
     * 播放OK声
     */
    fun playOk()

    /**
     * 播放警告声
     */
    fun playWarning()

    /**
     * 播放啊哦
     */
    fun playAhOh()

    /**
     * 播放滴
     */
    fun playDi()
}