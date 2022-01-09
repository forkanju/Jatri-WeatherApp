package com.forkan.weatherapp.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.forkan.weatherapp.datasource.Repository
import com.forkan.weatherapp.model.TempResponse
import com.forkan.weatherapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {


    var cityTempResponse: MutableLiveData<NetworkResult<TempResponse>> = MutableLiveData()

    fun getTemperature(queries: Map<String, String>) = viewModelScope.launch {
        getTemperatureSafeCall(queries)
    }


    private suspend fun getTemperatureSafeCall(queries: Map<String, String>) {
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getTemperature(queries)
                cityTempResponse.value = handleTempResponse(response)
            } catch (e: Exception) {
                cityTempResponse.value = NetworkResult.Error(""+e.message)
            }
        } else {
            cityTempResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }


    private fun handleTempResponse(response: Response<TempResponse>): NetworkResult<TempResponse>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("APP ID Limited.")
            }
            response.body()!!.dataList.isNullOrEmpty() -> {
                return NetworkResult.Error("Data not found.")
            }
            response.isSuccessful -> {
                val cityTempRes = response.body()
                return NetworkResult.Success(cityTempRes!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}