package com.example.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.room.dao.PurchaseDao
import com.example.data.room.models.PurchaseEntity

@Database(
    entities = [PurchaseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun purchaseDao(): PurchaseDao
}