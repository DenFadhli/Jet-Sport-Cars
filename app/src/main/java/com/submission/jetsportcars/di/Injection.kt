package com.submission.jetsportcars.di

import com.submission.jetsportcars.data.CarRepository

object Injection {
    fun provideCarRepository(): CarRepository {
        return CarRepository.getInstance()
    }
}