package com.example.lib_sound

import android.content.Context
import com.example.lib_sound.impl.SoundImpl
import com.example.lib_sound.inversion.ISound


/**
 * @author create by Linqy
 * @time 2024-10-14 : 9:42
 * function:声音管理类
 */
class SoundManager private constructor(private val context: Context) : ISound {
    //初始化
    init {
        //初始化音频
        SoundImpl.getInstance(context)
    }

    /**
     * 单例
     */
    companion object {
        private var instance: ISound? = null

        @Synchronized
        fun getInstance(context: Context): ISound {
            if (instance == null) {
                instance = SoundManager(context.applicationContext)
            }
            return instance!!
        }
    }


    override fun playOk() {
        SoundImpl.getInstance(context).playOk()
    }

    override fun playWarning() {
        SoundImpl.getInstance(context).playWarning()
    }

    override fun playAhOh() {
        SoundImpl.getInstance(context).playAhOh()
    }

    override fun playDi() {
        SoundImpl.getInstance(context).playDi()
    }
}