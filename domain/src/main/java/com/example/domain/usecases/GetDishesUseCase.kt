package com.example.domain.usecases

import com.example.domain.repository.ApiRepository

class GetDishesUseCase(private val apiRepository: ApiRepository) {
    suspend fun execute() = apiRepository.getDishes()
}