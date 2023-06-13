package com.example.data.room.utils

import com.example.data.room.models.PurchaseEntity
import com.example.domain.models.Purchase

fun PurchaseEntity.toPurchase() =
    Purchase(
        id = id,
        count = count,
        imageUrl = imageUrl,
        name = name,
        price = price,
        weight = weight
    )

fun Purchase.toPurchaseEntity() =
    PurchaseEntity(
        id = id,
        count = count,
        imageUrl = imageUrl,
        name = name,
        price = price,
        weight = weight
    )