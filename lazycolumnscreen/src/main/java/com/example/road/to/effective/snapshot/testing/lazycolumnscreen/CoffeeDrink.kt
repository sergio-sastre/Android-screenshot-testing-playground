package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

import androidx.annotation.DrawableRes

data class CoffeeDrink(
    val id: Long,
    val name: String,
    @DrawableRes val imageUrl: Int,
    val description: String,
    val ingredients: String,
    val orderDescription: String,
    val price: Double,
    val isFavourite: Boolean
)
