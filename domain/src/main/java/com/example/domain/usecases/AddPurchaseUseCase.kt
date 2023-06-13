package com.example.domain.usecases

import com.example.domain.models.Purchase
import com.example.domain.repository.PurchaseRepository

class AddPurchaseUseCase(private val repository: PurchaseRepository) {
    suspend fun execute(purchase: Purchase) {
        repository.addPurchase(purchase = purchase)
    }
}