package com.example.data.repository

import android.util.Log
import com.example.data.service.Api
import com.example.data.utils.toCategory
import com.example.data.utils.toDish
import com.example.domain.models.Category
import com.example.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ApiRepositoryImpl(private val api: Api) : ApiRepository {

    override suspend fun getCategories(): List<Category>? {
        val categoriesModel = api.getCategories()

        Log.d("MyLog", "categoriesModel = $categoriesModel")

        return categoriesModel?.categories?.map { it.toCategory() }
    }

    override suspend fun getDishes() = api.getDishes().dishes.map { it.toDish() }
}