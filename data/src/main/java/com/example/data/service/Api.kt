package com.example.data.service

import com.example.data.models.CategoriesModel
import com.example.data.models.DishesModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {

    @Headers("$CACHE_CONTROL$CACHE_LIFESPAN")
    @GET(CATEGORIES_API)
    suspend fun getCategories(): CategoriesModel

    @Headers("$CACHE_CONTROL$CACHE_LIFESPAN")
    @GET(DISHES_API)
    suspend fun getDishes(): DishesModel

    private companion object {
        private const val CACHE_CONTROL = "Cache-Control: max-age="
        private const val CACHE_LIFESPAN = "600" // 10 minutes
        private const val CATEGORIES_API = "058729bd-1402-4578-88de-265481fd7d54"
        private const val DISHES_API = "c7a508f2-a904-498a-8539-09d96785446e"
    }
}