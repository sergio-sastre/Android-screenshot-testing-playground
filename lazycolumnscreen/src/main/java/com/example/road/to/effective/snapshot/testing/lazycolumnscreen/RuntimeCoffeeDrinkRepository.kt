package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

object RuntimeCoffeeDrinkRepository : CoffeeDrinkRepository {
    private val coffeeDrinks: MutableList<CoffeeDrink> = initCoffeeDrinks()

    override suspend fun getCoffeeDrinks(): Flow<List<CoffeeDrink>> {
        return flowOf(coffeeDrinks)
    }

    override suspend fun getCoffeeDrink(id: Long): Flow<CoffeeDrink?> {
        return flowOf(
            coffeeDrinks.firstOrNull { it.id == id }
        )
    }

    override suspend fun updateFavouriteState(id: Long, newFavouriteState: Boolean): Flow<Boolean> {
        return flow {
            val position = coffeeDrinks.indexOfFirst { it.id == id }
            val result = if (position > -1) {
                val oldCoffeeDrink = coffeeDrinks.first { it.id == id }
                val newCoffeeDrink = oldCoffeeDrink.copy(isFavourite = newFavouriteState)
                coffeeDrinks.remove(oldCoffeeDrink)
                coffeeDrinks.add(position, newCoffeeDrink)
                true
            } else {
                false
            }
            emit(result)
        }
    }

    private fun initCoffeeDrinks(): MutableList<CoffeeDrink> {
        return DummyCoffeeDrinksDataSource().getCoffeeDrinks() as MutableList<CoffeeDrink>
    }
}
