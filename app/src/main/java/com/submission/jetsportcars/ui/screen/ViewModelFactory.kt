package com.submission.jetsportcars.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.submission.jetsportcars.data.CarRepository
import com.submission.jetsportcars.ui.screen.detail.DetailViewModel
import com.submission.jetsportcars.ui.screen.home.HomeViewModel

class ViewModelFactory(private val carRepository: CarRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(carRepository) as T
        }else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(carRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}