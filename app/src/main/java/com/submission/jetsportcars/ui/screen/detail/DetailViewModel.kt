package com.submission.jetsportcars.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.submission.jetsportcars.data.CarRepository
import com.submission.jetsportcars.model.CarList
import com.submission.jetsportcars.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val carRepository: CarRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CarList>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<CarList>>
        get() = _uiState

    fun getCarById(carId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(carRepository.getCarById(carId))
        }
    }
}