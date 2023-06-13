package com.example.testforeffectivemobile.presentation.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.AddPurchaseUseCase
import com.example.domain.usecases.GetDishesUseCase

class DishesViewModelFactory(
    private val addPurchaseUseCase: AddPurchaseUseCase,
    private val getDishesUseCase: GetDishesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        DishesViewModel(
            addPurchaseUseCase = addPurchaseUseCase,
            getDishesUseCase = getDishesUseCase
        ) as T
}