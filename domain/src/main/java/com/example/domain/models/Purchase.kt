package com.example.domain.models

data class Purchase(
    val id: Long = 0,
    val count: Int,
    val imageUrl: String?,
    val name: String,
    val price: Int,
    val weight: Int
)
