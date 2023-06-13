package com.example.domain.repository

import com.example.domain.models.Purchase

interface PurchaseRepository {

    suspend fun addPurchase(purchase: Purchase)

    suspend fun deletePurchase(id: Long)

    suspend fun getPurchases(): List<Purchase>
}