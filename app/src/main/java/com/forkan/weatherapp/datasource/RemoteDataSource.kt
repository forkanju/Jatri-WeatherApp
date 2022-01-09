package com.forkan.weatherapp.datasource

import com.forkan.weatherapp.model.TempResponse
import com.forkan.weatherapp.network.CityTempApi
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val cityTempApi: CityTempApi
) {

    suspend fun getTemperature(queries: Map<String, String>): Response<TempResponse> {
        return cityTempApi.getTemperature(queries)
    }
}

