package com.example.domain.usecases

import com.example.domain.repository.PurchaseRepository

class DeletePurchaseUseCase(private val repository: PurchaseRepository) {
    suspend fun execute(id: Long) {
        repository.deletePurchase(id = id)
    }
}