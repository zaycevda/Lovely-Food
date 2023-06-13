package com.example.testforeffectivemobile.di.modules

import com.example.domain.repository.ApiRepository
import com.example.domain.repository.PurchaseRepository
import com.example.domain.usecases.AddPurchaseUseCase
import com.example.domain.usecases.DeletePurchaseUseCase
import com.example.domain.usecases.GetCategoriesUseCase
import com.example.domain.usecases.GetDishesUseCase
import com.example.domain.usecases.GetPurchasesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideGetCategoriesUseCase(
        repository: ApiRepository
    ) = GetCategoriesUseCase(
        repository = repository
    )

    @Singleton
    @Provides
    fun provideGetDishesUseCase(
        repository: ApiRepository
    ) = GetDishesUseCase(
        repository = repository
    )

    @Singleton
    @Provides
    fun provideAddPurchaseUseCase(
        repository: PurchaseRepository
    ) = AddPurchaseUseCase(
        repository = repository
    )

    @Singleton
    @Provides
    fun provideDeletePurchaseUseCase(
        repository: PurchaseRepository
    ) = DeletePurchaseUseCase(
        repository = repository
    )

    @Singleton
    @Provides
    fun provideGetPurchasesUseCase(
        repository: PurchaseRepository
    ) = GetPurchasesUseCase(
        repository = repository
    )
}