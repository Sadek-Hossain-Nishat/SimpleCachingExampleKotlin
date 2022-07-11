package com.example.androidofflinecachingapp.api

import com.example.androidofflinecachingapp.data.Restaurant
import retrofit2.http.GET

interface RestaurantApi {
    companion object{
        val BASE_URL ="https://random-data-api.com/api/"
    }

    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurants():List<Restaurant>

}