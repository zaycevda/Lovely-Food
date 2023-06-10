package com.example.testforeffectivemobile.presentation.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.GetCategoriesUseCase

class CategoriesViewModelFactory(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        CategoriesViewModel(
            getCategoriesUseCase = getCategoriesUseCase
        ) as T
}