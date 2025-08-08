package com.example.lib_db.dagger

import android.content.Context
import androidx.room.Room
import com.example.lib_db.db.database.BusinessDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author create by Linqy
 * @time 2025-06-24 : 11:21
 * function:
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun businessDb(@ApplicationContext context: Context): BusinessDatabase {
        return Room.databaseBuilder(
            context,
            BusinessDatabase::class.java, BusinessDatabase.DATABASE_NAME
        )
            //.addMigrations(Migrations.BusinessDatabase_MIGRATION_1_2)
            .build()
    }
}