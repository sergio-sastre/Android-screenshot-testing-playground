package com.example.road.to.effective.snapshot.testing.coffeedrinkscompose

import kotlinx.coroutines.flow.Flow

interface CoffeeDrinkRepository {

    suspend fun getCoffeeDrinks(): Flow<List<CoffeeDrink>>

    suspend fun getCoffeeDrink(id: Long): Flow<CoffeeDrink?>

    suspend fun updateFavouriteState(id: Long, newFavouriteState: Boolean): Flow<Boolean>
}
