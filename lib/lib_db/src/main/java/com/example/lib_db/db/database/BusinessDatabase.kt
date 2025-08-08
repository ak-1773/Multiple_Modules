package com.example.lib_db.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lib_db.db.dao.BusinessDao
import com.example.lib_db.db.entity.ExampleEntity

/**
 * @author create by Linqy
 * @time 2025-06-20 : 18:12
 * function:
 */
@Database(
    entities = [ExampleEntity::class],
    version = 1,
    // 设置为false表示不导出数据库schema到文件，适合快速开发；设置为true则会生成版本历史JSON文件，适合管理数据库变更
    exportSchema = false
)
abstract class BusinessDatabase : RoomDatabase() {

    abstract fun businessDao(): BusinessDao

    //派生
    companion object {
        //数据库名称
        const val DATABASE_NAME = "business.db"
    }
}