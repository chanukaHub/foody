package com.chanuwa.foody.retrofit

import com.chanuwa.foody.models.CategoryListModel
import com.chanuwa.foody.models.MealsByCategoryListModel
import com.chanuwa.foody.models.MealListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealListModel>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id:String):Call<MealListModel>

    @GET("filter.php?")
    fun getPopularItems(@Query("c") categoryName:String): Call<MealsByCategoryListModel>

    @GET("categories.php")
    fun getCategories(): Call<CategoryListModel>
}