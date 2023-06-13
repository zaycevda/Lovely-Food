package com.example.domain.usecases

import com.example.domain.repository.PurchaseRepository

class GetPurchasesUseCase(private val repository: PurchaseRepository) {
    suspend fun execute() = repository.getPurchases()
}