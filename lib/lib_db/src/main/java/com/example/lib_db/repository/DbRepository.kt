package com.example.lib_db.repository

import com.example.lib_db.db.dao.BusinessDao
import com.example.lib_db.db.database.BusinessDatabase
import com.example.lib_db.db.entity.ExampleEntity
import javax.inject.Inject

/**
 * @author create by Linqy
 * @time 2025-06-24 : 11:41
 * function:
 */
class DbRepository @Inject constructor() : BusinessDao {
    @Inject
    lateinit var businessDatabase: BusinessDatabase
    override suspend fun queryName(): List<String> {
        return businessDatabase.businessDao().queryName()
    }


    override suspend fun queryByLastTime(): ExampleEntity? {
        return businessDatabase.businessDao().queryByLastTime()
    }

    override suspend fun batchInsertOrUpdate(entities: List<ExampleEntity>) {
        businessDatabase.businessDao().batchInsertOrUpdate(entities)
    }
}