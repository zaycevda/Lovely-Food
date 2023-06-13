package com.example.testforeffectivemobile.presentation.cart.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.AddPurchaseUseCase
import com.example.domain.usecases.DeletePurchaseUseCase
import com.example.domain.usecases.GetPurchasesUseCase

class CartViewModelFactory(
    private val addPurchaseUseCase: AddPurchaseUseCase,
    private val deletePurchaseUseCase: DeletePurchaseUseCase,
    private val getPurchasesUseCase: GetPurchasesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        CartViewModel(
            addPurchaseUseCase = addPurchaseUseCase,
            deletePurchaseUseCase = deletePurchaseUseCase,
            getPurchasesUseCase = getPurchasesUseCase
        ) as T
}