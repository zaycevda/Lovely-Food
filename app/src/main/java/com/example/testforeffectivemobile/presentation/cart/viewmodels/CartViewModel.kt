package com.example.testforeffectivemobile.presentation.cart.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Purchase
import com.example.domain.usecases.DeletePurchaseUseCase
import com.example.domain.usecases.GetPurchasesUseCase
import com.example.domain.usecases.UpdatePurchaseUseCase
import com.example.testforeffectivemobile.presentation.main.viewmodels.ScreenState
import com.example.testforeffectivemobile.presentation.main.viewmodels.ScreenState.ErrorScreenState
import com.example.testforeffectivemobile.presentation.main.viewmodels.ScreenState.LoadingScreenState
import com.example.testforeffectivemobile.presentation.main.viewmodels.ScreenState.SuccessScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val deletePurchaseUseCase: DeletePurchaseUseCase,
    private val getPurchasesUseCase: GetPurchasesUseCase,
    private val updatePurchaseUseCase: UpdatePurchaseUseCase
) : ViewModel() {

    private val _purchases: MutableStateFlow<ScreenState<List<Purchase>>> =
        MutableStateFlow(SuccessScreenState(listOf()))
    val purchases: StateFlow<ScreenState<List<Purchase>>> = _purchases

    fun deletePurchase(id: Long) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e(TAG, "deletePurchase: ${throwable.message}")
        }

        viewModelScope.launch(exceptionHandler) {
            Log.d(TAG, "deletePurchase: id = $id")
            deletePurchaseUseCase.execute(id)
            getPurchases()
        }
    }

    fun getPurchases() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _purchases.value = ErrorScreenState(throwable)
            Log.e(TAG, "getPurchases: $throwable")
        }

        viewModelScope.launch(exceptionHandler) {
            _purchases.value = LoadingScreenState()
            val purchases = getPurchasesUseCase.execute()
            Log.d(TAG, "getPurchases: purchases = $purchases")
            _purchases.value = SuccessScreenState(purchases)
        }
    }

    fun updatePurchase(purchase: Purchase) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e(TAG, "updatePurchase: ${throwable.message}")
        }

        viewModelScope.launch(exceptionHandler) {
            Log.d(TAG, "updatePurchase: purchase = $purchase")
            updatePurchaseUseCase.execute(purchase)
            getPurchases()
        }
    }

    private companion object {
        private const val TAG = "CartViewModelTag"
    }
}