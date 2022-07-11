package com.example.androidofflinecachingapp.features.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidofflinecachingapp.databinding.ActivityRestaurantBinding
import com.example.androidofflinecachingapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn


@AndroidEntryPoint
class RestaurantActivity : AppCompatActivity() {
    private val  viewModel:RestaurantViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val  restaurantAdapter =RestaurantAdapter()
        binding.apply {
            idRecyclerView.apply {
                adapter = restaurantAdapter
                layoutManager = LinearLayoutManager(this@RestaurantActivity)
            }
            viewModel.restaurants.observe(this@RestaurantActivity){result->
                restaurantAdapter.submitList(result.data)

                idProgressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                idTextviewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                idTextviewError.text = result.error?.localizedMessage

            }


        }
    }
}