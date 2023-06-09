package com.example.testforeffectivemobile.di.modules

import android.content.Context
import androidx.room.Room
import com.example.data.retrofit.repository.ApiRepositoryImpl
import com.example.data.retrofit.service.Api
import com.example.data.room.database.AppDatabase
import com.example.data.room.repository.PurchaseRepositoryImpl
import com.example.domain.repository.ApiRepository
import com.example.domain.repository.PurchaseRepository
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideApiRepository(
        api: Api
    ): ApiRepository = ApiRepositoryImpl(
        api = api
    )

    @Singleton
    @Provides
    fun provideApi(
        retrofit: Retrofit
    ): Api = retrofit
        .create(Api::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideOkhttpClient(
        context: Context
    ): OkHttpClient {
        val cache = Cache(context.cacheDir, CACHE_SIZE)

        return OkHttpClient().newBuilder()
            .cache(cache)
            .build()
    }

    @Singleton
    @Provides
    fun providePurchasesRepository(
        db: AppDatabase
    ): PurchaseRepository = PurchaseRepositoryImpl(
        db = db
    )

    @Singleton
    @Provides
    fun provideRoom(
        context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        APP_DATABASE
    ).build()

    private companion object {
        private const val APP_DATABASE = "app_database"
        private const val BASE_URL = "https://run.mocky.io/v3/"
        private const val CACHE_SIZE: Long = 10 * 1024 * 1024 // 10 MB
    }
}