package com.example.testforeffectivemobile.di.modules

import com.example.domain.repository.ApiRepository
import com.example.domain.usecases.GetCategoriesUseCase
import com.example.domain.usecases.GetDishesUseCase
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
}