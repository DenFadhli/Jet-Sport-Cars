package com.submission.jetsportcars.data

import com.submission.jetsportcars.model.CarList
import com.submission.jetsportcars.model.DummyCar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CarRepository {
    private val dataCar = mutableListOf<CarList>()

    init {
        if (dataCar.isEmpty()) {
            DummyCar.cars.forEach {
                dataCar.add(CarList(it, 0))
            }
        }
    }

    fun getCarById(carId: Long): CarList{
        return dataCar.first {
            it.car.id == carId
        }
    }

    fun getAllCars(): Flow<List<CarList>> {
        return flowOf(dataCar)
    }

    companion object {
        @Volatile
        private var instance: CarRepository? = null

        fun getInstance(): CarRepository =
            instance ?: synchronized(this) {
                CarRepository().apply {
                    instance = this
                }
            }
    }
}