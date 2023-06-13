package com.example.testforeffectivemobile.presentation.cart.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.DeletePurchaseUseCase
import com.example.domain.usecases.GetPurchasesUseCase
import com.example.domain.usecases.UpdatePurchaseUseCase

class CartViewModelFactory(
    private val deletePurchaseUseCase: DeletePurchaseUseCase,
    private val getPurchasesUseCase: GetPurchasesUseCase,
    private val updatePurchaseUseCase: UpdatePurchaseUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        CartViewModel(
            deletePurchaseUseCase = deletePurchaseUseCase,
            getPurchasesUseCase = getPurchasesUseCase,
            updatePurchaseUseCase = updatePurchaseUseCase
        ) as T
}