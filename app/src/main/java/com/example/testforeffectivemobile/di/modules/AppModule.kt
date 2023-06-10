package com.example.testforeffectivemobile.di.modules

import android.content.Context
import com.example.domain.usecases.GetCategoriesUseCase
import com.example.testforeffectivemobile.presentation.main.viewmodels.CategoriesViewModelFactory
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
}