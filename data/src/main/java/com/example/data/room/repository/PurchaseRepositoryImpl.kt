package com.example.data.room.repository

import android.util.Log
import com.example.data.room.database.AppDatabase
import com.example.data.room.utils.toPurchase
import com.example.data.room.utils.toPurchaseEntity
import com.example.domain.models.Purchase
import com.example.domain.repository.PurchaseRepository

class PurchaseRepositoryImpl(db: AppDatabase) : PurchaseRepository {

    private val dao = db.purchaseDao()

    override suspend fun addPurchase(purchase: Purchase) {
        val purchaseEntity = purchase.toPurchaseEntity()

        Log.d(TAG, "addPurchase: purchaseEntity = $purchaseEntity")

        dao.insert(purchaseEntity)
    }

    override suspend fun deletePurchase(id: Long) {
        Log.d(TAG, "deletePurchase: id = $id")

        dao.delete(id)
    }

    override suspend fun getPurchases(): List<Purchase> {
        val purchasesEntity = dao.getAll()

        Log.d(TAG, "getPurchases: purchasesEntity = $purchasesEntity")

        return purchasesEntity.map { it.toPurchase() }
    }

    private companion object {
        private const val TAG = "PurchaseRepositoryImpl"
    }
}