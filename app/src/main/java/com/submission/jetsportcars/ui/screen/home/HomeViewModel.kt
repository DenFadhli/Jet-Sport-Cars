package com.submission.jetsportcars.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.submission.jetsportcars.data.CarRepository
import com.submission.jetsportcars.model.CarList
import com.submission.jetsportcars.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val carRepository: CarRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<CarList>>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<CarList>>>
        get() = _uiState

    fun getAllCars() {
        viewModelScope.launch {
            carRepository.getAllCars()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { dataCar ->
                    _uiState.value = UiState.Success(dataCar)
                }
        }
    }
}