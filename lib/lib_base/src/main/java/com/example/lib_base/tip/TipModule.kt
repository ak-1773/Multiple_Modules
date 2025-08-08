package com.example.lib_base.tip

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author create by Linqy
 * @time 2024-10-14 : 14:09
 * function:
 */
@InstallIn(SingletonComponent::class)
@Module
class TipModule {
    @Provides
    @Singleton
    fun provideTip(@ApplicationContext context: Context): ITip {
        return Tip(context)
    }
}