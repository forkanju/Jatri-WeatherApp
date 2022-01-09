package com.forkan.weatherapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class DataList (

    @SerializedName("id"      ) var id      : Int?               = null,
    @SerializedName("name"    ) var name    : String?            = null,
    @SerializedName("coord"   ) var coord   : @RawValue Coord?             = Coord(),
    @SerializedName("main"    ) var main    : @RawValue Main?              = Main(),
    @SerializedName("dt"      ) var dt      : Int?               = null,
    @SerializedName("wind"    ) var wind    : @RawValue Wind?              = Wind(),
    @SerializedName("sys"     ) var sys     : @RawValue Sys?               = Sys(),
    @SerializedName("rain"    ) var rain    : String?            = null,
    @SerializedName("snow"    ) var snow    : String?            = null,
    @SerializedName("clouds"  ) var clouds  : @RawValue Clouds?            = Clouds(),
    @SerializedName("weather" ) var weather : @RawValue ArrayList<Weather> = arrayListOf()

): Parcelable