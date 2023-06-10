package com.example.data.repository

import android.util.Log
import com.example.data.service.Api
import com.example.data.utils.toCategory
import com.example.data.utils.toDish
import com.example.domain.models.Category
import com.example.domain.models.Dish
import com.example.domain.repository.ApiRepository

class ApiRepositoryImpl(private val api: Api) : ApiRepository {

    override suspend fun getCategories(): List<Category>? {
        val categoriesModel = api.getCategories()

        Log.d(TAG, "getCategories: categoriesModel = $categoriesModel")

        return categoriesModel?.categories?.map { it.toCategory() }
    }

    override suspend fun getDishes(): List<Dish>? {
        val dishesModel = api.getDishes()

        Log.d(TAG, "getDishes: dishesModel = $dishesModel")

        return dishesModel?.dishes?.map { it.toDish() }
    }

    private companion object {
        private const val TAG = "ApiRepositoryImplTag"
    }
}