package com.example.lib_db.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {
    val BusinessDatabase_MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            /* //1. 创建新表
             database.execSQL("CREATE TABLE IF NOT EXISTS `resultRecordNew` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `parentId` INTEGER NOT NULL, `outType` INTEGER, `remarks` TEXT, `scanTime` TEXT, `barcodeType` TEXT, `fourthpuiler` TEXT, `isScanUpload` TEXT, `checkedFlag` INTEGER NOT NULL, `barcode` TEXT NOT NULL, `dealerId` TEXT, `serialType` TEXT, `vaildTime` TEXT, `productId` TEXT, `productName` TEXT, `itemId` TEXT, `itemName` TEXT, `price` TEXT, `destinationId` TEXT, `destinationName` TEXT, `status` TEXT, `codeNum` TEXT, `productTime` TEXT)")
             database.execSQL("CREATE INDEX IF NOT EXISTS `index_resultRecord_parentId_barcode_checkedFlag` ON `resultRecordNew` (`parentId`, `barcode`, `checkedFlag`)")
             //2. 复制旧表到新表
             database.execSQL("INSERT INTO 'resultRecordNew' (id, parentId, outType, remarks, scanTime, barcodeType, fourthpuiler, isScanUpload, checkedFlag, barcode, dealerId, serialType, vaildTime, productId, productName, itemId, itemName, price, destinationId, destinationName, status, codeNum) SELECT id, parentId, outType, remarks, scanTime, barcodeType, fourthpuiler, isScanUpload, checkedFlag, barcode, dealerId, serialType, vaildTime, productId, productName, itemId, itemName, price, destinationId, destinationName, status, codeNum FROM `resultRecord`")
             //3. 删除旧表
             database.execSQL("DROP TABLE IF EXISTS `resultRecord`")
             //4. 重命名新表名称为旧表名称
             database.execSQL("ALTER TABLE `resultRecordNew` RENAME TO `resultRecord`")*/
            //增加 productTime 字段
            database.execSQL("ALTER TABLE `resultRecord` ADD `productTime` TEXT default ''")
        }
    }


}