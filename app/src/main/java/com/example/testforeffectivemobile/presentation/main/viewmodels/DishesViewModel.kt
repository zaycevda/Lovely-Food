package com.example.testforeffectivemobile.presentation.main.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Dish
import com.example.domain.models.Purchase
import com.example.domain.usecases.AddPurchaseUseCase
import com.example.domain.usecases.GetDishesUseCase
import com.example.testforeffectivemobile.presentation.main.viewmodels.ScreenState.ErrorScreenState
import com.example.testforeffectivemobile.presentation.main.viewmodels.ScreenState.LoadingScreenState
import com.example.testforeffectivemobile.presentation.main.viewmodels.ScreenState.SuccessScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DishesViewModel(
    private val addPurchaseUseCase: AddPurchaseUseCase,
    private val getDishesUseCase: GetDishesUseCase
) : ViewModel() {

    private val _dishes: MutableStateFlow<ScreenState<List<Dish>?>> =
        MutableStateFlow(SuccessScreenState(listOf()))
    val dishes: StateFlow<ScreenState<List<Dish>?>> = _dishes

    fun addPurchase(purchase: Purchase) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e(TAG, "addPurchase: ${throwable.message}")
        }

        viewModelScope.launch(exceptionHandler) {
            Log.d(TAG, "addPurchase: purchase = $purchase")
            addPurchaseUseCase.execute(purchase)
        }
    }

    fun getDishes() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _dishes.value = ErrorScreenState(throwable)
            Log.e(TAG, "getDishes: ${throwable.message}")
        }

        viewModelScope.launch(exceptionHandler) {
            _dishes.value = LoadingScreenState()
            val dishes = getDishesUseCase.execute()
            Log.d(TAG, "getDishes: dishes = $dishes")
            _dishes.value = SuccessScreenState(dishes)
        }
    }

    private companion object {
        private const val TAG = "DishesViewModelTag"
    }
}