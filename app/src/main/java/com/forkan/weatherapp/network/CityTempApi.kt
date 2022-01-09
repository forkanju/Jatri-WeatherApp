package com.forkan.weatherapp.network

import com.forkan.weatherapp.model.TempResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface CityTempApi {

    @GET("/data/2.5/find")
    suspend fun getTemperature(
        @QueryMap queries: Map<String, String>
    ): Response<TempResponse>
}