package com.example.lib_sound.impl

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Build
import com.example.lib_sound.config.SoundConfig
import com.example.lib_sound.inversion.ISound
import com.example.lib_sound.R


/**
 * @author create by Linqy
 * @time 2024-10-14 : 10:10
 * function:声音播放实现类
 */
class SoundImpl private constructor(context: Context) : ISound {
    private var soundPool: SoundPool? = null
    private var ok: Int? = null
    private var warning: Int? = null
    private var ahoh: Int? = null
    private var di: Int? = null

    //初始化
    init {
        soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SoundPool.Builder()
                .setMaxStreams(SoundConfig.MAX_STREAMS)
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setLegacyStreamType(SoundConfig.STREAM_TYPE).build()
                )
                .build()
        } else {
            SoundPool(SoundConfig.MAX_STREAMS, SoundConfig.STREAM_TYPE, SoundConfig.SRC_QUALITY)
        }
        ok = soundPool?.load(context, R.raw.ok, SoundConfig.PRIORITY)
        warning = soundPool?.load(context, R.raw.warning, SoundConfig.PRIORITY)
        ahoh = soundPool?.load(context, R.raw.ahoh, SoundConfig.PRIORITY)
        di = soundPool?.load(context, R.raw.di, SoundConfig.PRIORITY)
    }

    /**
     * 单例
     */
    companion object {
        private var instance: ISound? = null

        @Synchronized
        fun getInstance(context: Context): ISound {
            if (instance == null) {
                instance = SoundImpl(context.applicationContext)
            }
            return instance!!
        }
    }

    /**
     * 播放OK声
     */
    override fun playOk() {
        ok?.apply {
            soundPool?.play(
                this,
                SoundConfig.LEFT_VOLUME,
                SoundConfig.RIGHT_VOLUME,
                SoundConfig.PRIORITY,
                SoundConfig.LOOP,
                SoundConfig.RATE
            )
        }

    }

    /**
     * 播放警告声
     */
    override fun playWarning() {
        warning?.apply {
            soundPool?.play(
                this,
                SoundConfig.LEFT_VOLUME,
                SoundConfig.RIGHT_VOLUME,
                SoundConfig.PRIORITY,
                SoundConfig.LOOP,
                SoundConfig.RATE
            )
        }
    }

    /**
     * 播放啊哦
     */
    override fun playAhOh() {
        ahoh?.apply {
            soundPool?.play(
                this,
                SoundConfig.LEFT_VOLUME,
                SoundConfig.RIGHT_VOLUME,
                SoundConfig.PRIORITY,
                SoundConfig.LOOP,
                SoundConfig.RATE
            )
        }
    }

    /**
     * 播放滴滴
     */
    override fun playDi() {
        di?.apply {
            soundPool?.play(
                this,
                SoundConfig.LEFT_VOLUME,
                SoundConfig.RIGHT_VOLUME,
                SoundConfig.PRIORITY,
                SoundConfig.LOOP + 2,
                SoundConfig.RATE
            )
        }
    }
}