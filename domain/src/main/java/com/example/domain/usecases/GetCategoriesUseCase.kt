package com.example.domain.usecases

import com.example.domain.repository.ApiRepository

class GetCategoriesUseCase(private val apiRepository: ApiRepository) {
    suspend fun execute() = apiRepository.getCategories()
}