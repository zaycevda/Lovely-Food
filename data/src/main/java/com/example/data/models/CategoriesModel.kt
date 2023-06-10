package com.example.data.models

import com.google.gson.annotations.SerializedName

data class CategoriesModel(
    @SerializedName("сategories")
    val categories: List<CategoryModel>
)