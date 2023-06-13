package com.example.testforeffectivemobile.di.modules

import android.content.Context
import com.example.domain.usecases.AddPurchaseUseCase
import com.example.domain.usecases.DeletePurchaseUseCase
import com.example.domain.usecases.GetCategoriesUseCase
import com.example.domain.usecases.GetDishesUseCase
import com.example.domain.usecases.GetPurchasesUseCase
import com.example.domain.usecases.UpdatePurchaseUseCase
import com.example.testforeffectivemobile.presentation.cart.viewmodels.CartViewModelFactory
import com.example.testforeffectivemobile.presentation.main.viewmodels.CategoriesViewModelFactory
import com.example.testforeffectivemobile.presentation.main.viewmodels.DishesViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideCategoriesViewModelFactory(
        getCategoriesUseCase: GetCategoriesUseCase
    ) = CategoriesViewModelFactory(
        getCategoriesUseCase = getCategoriesUseCase
    )

    @Singleton
    @Provides
    fun provideDishesViewModelFactory(
        addPurchaseUseCase: AddPurchaseUseCase,
        getDishesUseCase: GetDishesUseCase
    ) = DishesViewModelFactory(
        addPurchaseUseCase = addPurchaseUseCase,
        getDishesUseCase = getDishesUseCase
    )

    @Singleton
    @Provides
    fun provideCartViewModelFactory(
        deletePurchaseUseCase: DeletePurchaseUseCase,
        getPurchasesUseCase: GetPurchasesUseCase,
        updatePurchaseUseCase: UpdatePurchaseUseCase
    ) = CartViewModelFactory(
        deletePurchaseUseCase = deletePurchaseUseCase,
        getPurchasesUseCase = getPurchasesUseCase,
        updatePurchaseUseCase = updatePurchaseUseCase
    )
}