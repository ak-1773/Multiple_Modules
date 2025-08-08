package com.example.lib_db.db.entity

import androidx.room.Entity
import androidx.room.Index

/**
 * @author create by Linqy
 * @time 2025-06-20 : 18:16
 * function:
 */
@Entity(
    tableName = "ExampleRecord",
    primaryKeys = ["id"],
    indices = [Index(value = ["id", "code", "name", "lastTime"])]
)
data class ExampleEntity(
    var id: String = "",
    var code: String = "",
    var name: String = "",
    var volumes: Int = 0,
    var lastTime: Long = 0,
)