package tk.mohithaiyappa.coffeepricesm3.ui.home

sealed class HomeUiState

data object Loading : HomeUiState()

data class Error(val errorMsg: String) : HomeUiState()

data class Result<T>(val data: T) : HomeUiState()
