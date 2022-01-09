package com.forkan.weatherapp.model

import com.google.gson.annotations.SerializedName


data class TempResponse (

  @SerializedName("message" ) var message : String?         = null,
  @SerializedName("cod"     ) var cod     : String?         = null,
  @SerializedName("count"   ) var count   : Int?            = null,
  @SerializedName("list"    ) var dataList    : ArrayList<DataList> = arrayListOf()

)