package com.forkan.weatherapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.forkan.weatherapp.databinding.TempRowLayoutBinding
import com.forkan.weatherapp.model.DataList
import com.forkan.weatherapp.model.TempResponse
import com.forkan.weatherapp.utils.TempDiffUtil

class TempAdapter : RecyclerView.Adapter<TempAdapter.MyViewHolder>() {


    private var temperatures = emptyList<DataList>()

    class MyViewHolder(private val binding: TempRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dataList: DataList) {
            binding.data = dataList
            binding.executePendingBindings() //will update our view with new data when it will change
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TempRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTemps = temperatures[position]
        holder.bind(currentTemps)

    }

    override fun getItemCount(): Int {
        return temperatures.size
    }


    //It will update only those value are not same instead of notifyDataSetChanged()
    fun setData(newData: TempResponse) {
        val tempDiffUtil = TempDiffUtil(temperatures, newData.dataList)
        val diffUtilData = DiffUtil.calculateDiff(tempDiffUtil)
        temperatures = newData.dataList
        diffUtilData.dispatchUpdatesTo(this)
    }
}
