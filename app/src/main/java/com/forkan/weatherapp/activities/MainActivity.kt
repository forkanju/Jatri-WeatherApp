package com.forkan.weatherapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.forkan.weatherapp.R
import com.forkan.weatherapp.adapters.TempAdapter
import com.forkan.weatherapp.utils.Constants.Companion.APP_ID
import com.forkan.weatherapp.utils.NetworkResult
import com.forkan.weatherapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { TempAdapter() }
    private lateinit var mRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]


        setupRecyclerView()

        requestApiData()

    }


    private fun requestApiData() {
        mainViewModel.getTemperature(applyQueries())

        mainViewModel.cityTempResponse.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(this, response.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    Toast.makeText(this, "Loading..", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries["lat"] = "23.68"
        queries["lon"] = "90.35"
        queries["cnt"] = "50"
        queries["appid"] = APP_ID

        return queries
    }

    private fun setupRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)

    }
}