package com.example.lib_db.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lib_db.db.entity.ExampleEntity

/**
 * @author create by Linqy
 * @time 2025-06-24 : 10:57
 * function:
 */
@Dao
interface BusinessDao {
    /**
     * 查询
     */
    @Query("SELECT name FROM ExampleRecord GROUP BY name ORDER BY name ASC")
    suspend fun queryName(): List<String>

    /**
     * 查询最后一次更新
     */
    @Query("SELECT * FROM ExampleRecord ORDER BY lastTime DESC LIMIT 1")
    suspend fun queryByLastTime(): ExampleEntity?

    /**
     * 批量插入或更新
     * @param entities
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun batchInsertOrUpdate(entities: List<ExampleEntity>)
}