package com.example.androidofflinecachingapp.data

import androidx.room.withTransaction
import com.example.androidofflinecachingapp.api.RestaurantApi
import com.example.androidofflinecachingapp.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: RestaurantApi,
    private val db:RestaurantDatabase
) {

    private val restaurantDao = db.restaurantDao()

    fun getRestaurants() = networkBoundResource(
        query = {
            restaurantDao.getAllRestaurants()

        },
        fetch = {
            delay(1000)
            api.getRestaurants()
        },
        saveFetchResult = {restaurants ->
            db.withTransaction {
                restaurantDao.deleteAllRestaurants()
                restaurantDao.insertRestaurants(restaurants)
            }

        }

    )
}