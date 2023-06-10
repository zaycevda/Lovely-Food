package com.example.data.models

import com.google.gson.annotations.SerializedName

data class DishesModel(
    @SerializedName("dishes")
    val dishes: List<DishModel>
)