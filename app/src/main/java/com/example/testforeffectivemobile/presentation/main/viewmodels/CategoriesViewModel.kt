package com.example.testforeffectivemobile.presentation.main.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Category
import com.example.domain.usecases.GetCategoriesUseCase
import com.example.testforeffectivemobile.presentation.main.viewmodels.ScreenState.ErrorScreenState
import com.example.testforeffectivemobile.presentation.main.viewmodels.ScreenState.LoadingScreenState
import com.example.testforeffectivemobile.presentation.main.viewmodels.ScreenState.SuccessScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categories: MutableStateFlow<ScreenState<List<Category?>?>>
    = MutableStateFlow(SuccessScreenState(null))
    val categories: StateFlow<ScreenState<List<Category?>?>> = _categories

    fun getCategories() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _categories.value = ErrorScreenState(throwable)
            Log.e(TAG, "getCategories: ${throwable.message}")
        }

        viewModelScope.launch(exceptionHandler) {
            _categories.value = LoadingScreenState()
            val categories = getCategoriesUseCase.execute()
            Log.d(TAG, "getCategories: categories = $categories")
            _categories.value = SuccessScreenState(categories)
        }
    }

    private companion object {
        private const val TAG = "CategoriesViewModelTag"
    }
}