package com.example.androidofflinecachingapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface RestaurantDao {
    @Query("select * from restaurants")
    fun getAllRestaurants():Flow<List<Restaurant>>



    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants:List<Restaurant>)

    @Query("delete from restaurants")
    suspend fun deleteAllRestaurants()
}