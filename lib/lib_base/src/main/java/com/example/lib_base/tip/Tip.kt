package com.example.lib_base.tip

import android.content.Context
import com.example.lib_sound.SoundManager
import com.example.lib_sound.inversion.ISound
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * @author create by Linqy
 * @time 2024-10-14 : 11:20
 * function:提示
 */
class Tip @Inject constructor(
    @ApplicationContext val context: Context,
) : ITip {
    val  soundManager: ISound

    init {
        //初始化音频
        soundManager = SoundManager.getInstance(context)
    }
    override fun ok() {
        //ok
        soundManager.playOk()
    }

    override fun warning() {
        //warning
        soundManager.playWarning()
    }

    override fun ahOh() {
        //啊哦
        soundManager.playAhOh()
    }

    override fun di() {
        //滴
        soundManager.playDi()
    }
}