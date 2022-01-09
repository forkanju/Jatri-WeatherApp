package com.forkan.weatherapp.adapters

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.forkan.weatherapp.model.DataList

class TempRowBinding {

    companion object {

        fun itemClickListener(tempLayout: ConstraintLayout, datalist: DataList) {
            // rv_item_temp
            tempLayout.setOnClickListener {

            }
        }


        @BindingAdapter("setHumidity")
        @JvmStatic
        fun setHumidity(textView: TextView, temp: Double) {
            val tempInt = temp.toInt()
            val tempInString = (tempInt - 273).toString()
            val tempInDegreeDelicious = "$tempInString°C"
            textView.text = tempInDegreeDelicious
        }
    }
}