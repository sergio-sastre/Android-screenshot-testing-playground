package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoffeeDrinksViewModelFactory(
    private val repository: CoffeeDrinkRepository,
    private val mapper: CoffeeDrinkItemMapper
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CoffeeDrinksViewModel(repository, mapper) as T
}