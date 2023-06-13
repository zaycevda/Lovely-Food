package com.example.data.retrofit.models

import com.google.gson.annotations.SerializedName

data class CategoriesModel(
    @SerializedName("сategories")
    val categories: List<CategoryModel>
)