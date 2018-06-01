package io.keepcoding.guedrbootcamp6.model

import io.keepcoding.guedrbootcamp6.R

class Cities {

    private val cities: List<City> = listOf(
            City("Madrid", Forecast(25f, 10f, 35f, "Soleado con alguna nube", R.drawable.ico_02)),
            City("Jaén", Forecast(36f, 23f, 19f, "Sol a tope", R.drawable.ico_01)),
            City("Quito", Forecast(30f, 15f, 40f, "Lluvia", R.drawable.ico_10))
    )

    val count
        get() = cities.size

    fun getCity(index: Int) = cities[index]
    operator fun get(index: Int) = cities[index]
    fun toArray() = cities.toTypedArray()
}