package tk.mohithaiyappa.coffeepricesm3.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tk.mohithaiyappa.coffeepricesm3.domain.repo.SpicePriceRepo
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(val spicePriceRepo: SpicePriceRepo) : ViewModel() {
        private val _uiState = MutableStateFlow<HomeUiState>(Loading)
        val uiState: StateFlow<HomeUiState> = _uiState

        init {
            loadSpiceData()
        }

        fun loadSpiceData() {
            viewModelScope.launch(Dispatchers.IO) {
                spicePriceRepo.getAllSpicePrice().apply {
                    _uiState.value = Result(data = this)
                }
            }
        }
    }
