package com.example.domain.usecases

import com.example.domain.repository.ApiRepository

class GetCategoriesUseCase(private val repository: ApiRepository) {
    suspend fun execute() = repository.getCategories()
}