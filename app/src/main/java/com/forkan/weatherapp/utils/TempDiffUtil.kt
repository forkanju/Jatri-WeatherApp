package com.forkan.weatherapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.forkan.weatherapp.model.DataList

class TempDiffUtil(
    private val oldList: List<DataList>,
    private val newDataList: ArrayList<DataList>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newDataList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newDataList[newItemPosition]  //three equal sign is identical operator
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newDataList[newItemPosition]
    }

}