package com.example.road.to.effective.snapshot.testing.compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CoffeeDrinksViewModel(
    private val repository: CoffeeDrinkRepository,
    private val mapper: CoffeeDrinkItemMapper
) : ViewModel() {

    private val _uiState: MutableLiveData<UiState<CoffeeDrinksState>> = MutableLiveData()
    val uiState: LiveData<UiState<CoffeeDrinksState>>
        get() = _uiState

    fun loadCoffeeDrinks() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getCoffeeDrinks()
                .map { coffeeDrinks ->
                    coffeeDrinks.map { mapper.map(it) }
                }
                .collect {
                    _uiState.value = UiState.Success(
                        CoffeeDrinksState(it)
                    )
                }
        }
    }
}
