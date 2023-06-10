package com.example.domain.models

data class Dish(
    val id: Long,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val imageUrl: String?,
    val tags: List<String>
)