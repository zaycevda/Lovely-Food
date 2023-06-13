package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.room.models.PurchaseEntity

@Dao
interface PurchaseDao {

    @Query("DELETE from purchases WHERE id = :id")
    suspend fun delete(id: Long)

    @Insert
    suspend fun insert(purchaseEntity: PurchaseEntity)

    @Query("SELECT * FROM purchases")
    suspend fun getAll(): List<PurchaseEntity>

    @Update
    suspend fun update(purchaseEntity: PurchaseEntity)
}