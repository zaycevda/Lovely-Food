package com.example.data.retrofit.utils

import com.example.data.retrofit.models.CategoryModel
import com.example.data.retrofit.models.DishModel
import com.example.domain.models.Category
import com.example.domain.models.Dish

fun CategoryModel.toCategory() =
    Category(
        id = id,
        name = name,
        imageUrl = imageUrl
    )

fun DishModel.toDish() =
    Dish(
        id = id,
        name = name,
        price = price,
        weight = weight,
        description = description,
        imageUrl = imageUrl,
        tags = tags
    )