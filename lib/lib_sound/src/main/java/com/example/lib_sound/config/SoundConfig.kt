package com.example.lib_sound.config

import android.media.AudioManager

/**
 * @author create by Linqy
 * @time 2024-10-14 : 9:44
 * function: 声音配置类
 */
object SoundConfig {
    const val MAX_STREAMS = 10
    const val STREAM_TYPE = AudioManager.STREAM_SYSTEM
    const val SRC_QUALITY = 8
    const val LEFT_VOLUME = 1.0f
    const val RIGHT_VOLUME = 1.0f
    const val PRIORITY = 5
    const val LOOP = 0
    const val RATE = 1.0f
}