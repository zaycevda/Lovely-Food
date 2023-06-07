package com.example.testforeffectivemobile.di.components

import com.example.testforeffectivemobile.di.modules.AppModule
import com.example.testforeffectivemobile.di.modules.DataModule
import com.example.testforeffectivemobile.di.modules.DomainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DomainModule::class
    ]
)
interface AppComponent