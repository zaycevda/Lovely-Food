package com.example.data.models

import com.google.gson.annotations.SerializedName

data class DishModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("tegs")
    val tags: List<String>
)