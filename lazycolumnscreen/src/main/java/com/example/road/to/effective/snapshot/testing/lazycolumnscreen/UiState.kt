package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

sealed class UiState<out T : Any?> {

    object Loading : UiState<Nothing>()

    data class Success<out T : Any>(val data: T) : UiState<T>()
}
