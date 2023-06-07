package com.example.domain.repository

import com.example.domain.models.Category
import com.example.domain.models.Dish

interface ApiRepository {

    suspend fun getCategories(): List<Category>

    suspend fun getDishes(): List<Dish>
}