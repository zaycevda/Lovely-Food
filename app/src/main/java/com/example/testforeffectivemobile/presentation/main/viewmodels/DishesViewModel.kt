package com.example.testforeffectivemobile.presentation.main.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Dish
import com.example.domain.usecases.GetDishesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DishesViewModel(
    private val getDishesUseCase: GetDishesUseCase
) : ViewModel() {

    private val _dishes: MutableStateFlow<ScreenState<List<Dish>?>>
            = MutableStateFlow(ScreenState.SuccessScreenState(listOf()))
    val dishes: StateFlow<ScreenState<List<Dish>?>> = _dishes

    fun getDishes() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _dishes.value = ScreenState.ErrorScreenState(throwable)
            Log.e(TAG, "getDishes: ${throwable.message}")
        }

        viewModelScope.launch(exceptionHandler) {
            _dishes.value = ScreenState.LoadingScreenState()
            val dishes = getDishesUseCase.execute()
            Log.d(TAG, "getDishes: dishes = $dishes")
            _dishes.value = ScreenState.SuccessScreenState(dishes)
        }
    }

    private companion object {
        private const val TAG = "DishesViewModelTag"
    }
}