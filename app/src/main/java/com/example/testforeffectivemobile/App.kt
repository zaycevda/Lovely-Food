package com.example.testforeffectivemobile

import android.app.Application
import com.example.testforeffectivemobile.di.components.AppComponent
import com.example.testforeffectivemobile.di.components.DaggerAppComponent
import com.example.testforeffectivemobile.di.modules.AppModule
import com.example.testforeffectivemobile.di.modules.DataModule
import com.example.testforeffectivemobile.di.modules.DomainModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        componet = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule())
            .domainModule(DomainModule())
            .build()
    }

    companion object {
        lateinit var componet: AppComponent
            private set
    }
}