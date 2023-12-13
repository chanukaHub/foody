package com.chanuwa.foody.retrofit

import com.chanuwa.foody.models.MealListModel
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealListModel>
}