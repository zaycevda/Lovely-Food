package com.example.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchases")
data class PurchaseEntity(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo("count")
    val count: Int,
    @ColumnInfo("image_url")
    val imageUrl: String?,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("price")
    val price: Int,
    @ColumnInfo("weight")
    val weight: Int
)