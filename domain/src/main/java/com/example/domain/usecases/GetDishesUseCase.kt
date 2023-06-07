package com.example.domain.usecases

import com.example.domain.repository.ApiRepository

class GetDishesUseCase(private val repository: ApiRepository) {
    suspend fun execute() = repository.getDishes()
}