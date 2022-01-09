package com.forkan.weatherapp.model

import com.google.gson.annotations.SerializedName


data class Sys (

  @SerializedName("country" ) var country : String? = null

)