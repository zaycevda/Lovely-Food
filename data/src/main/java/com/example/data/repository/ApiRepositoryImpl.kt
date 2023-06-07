package com.example.data.repository

import com.example.data.service.Api
import com.example.data.utils.toCategory
import com.example.data.utils.toDish
import com.example.domain.repository.ApiRepository

class ApiRepositoryImpl(private val api: Api) : ApiRepository {

    override suspend fun getCategories() = api.getCategories().categories.map { it.toCategory() }

    override suspend fun getDishes() = api.getDishes().dishes.map { it.toDish() }
}