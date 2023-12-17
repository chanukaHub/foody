package com.chanuwa.foody.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanuwa.foody.models.CategoryListModel
import com.chanuwa.foody.models.MealsByCategoryListModel
import com.chanuwa.foody.models.MealListModel
import com.chanuwa.foody.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel() {
    private var randomMealLiveData = MutableLiveData<MealListModel.Meal>()
    private var popularMealLiveData = MutableLiveData<List<MealsByCategoryListModel.Meal?>?>()
    private var categoriesLiveData = MutableLiveData<List<CategoryListModel.Category?>?>()


    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealListModel> {
            override fun onResponse(call: Call<MealListModel>, response: Response<MealListModel>) {
                if (response.body() != null) {
                    val meal: MealListModel.Meal? = response.body()!!.meals!![0]
                    randomMealLiveData.value = meal!!
                }else{
                    return
                }
            }
            override fun onFailure(call: Call<MealListModel>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }
    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<MealsByCategoryListModel>{
            override fun onResponse(call: Call<MealsByCategoryListModel>, response: Response<MealsByCategoryListModel>) {
                if (response.body() != null) {
                    popularMealLiveData.value = response.body()!!.meals
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealsByCategoryListModel>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }

    fun getCategories(){
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryListModel>{
            override fun onResponse(call: Call<CategoryListModel>, response: Response<CategoryListModel>) {
                response.body()?.let { categoryList ->
                    categoriesLiveData.postValue(categoryList.categories)
                }
            }

            override fun onFailure(call: Call<CategoryListModel>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }

    fun observeRandomMealLivedata(): LiveData<MealListModel.Meal>{
        return randomMealLiveData
    }

    fun observePopularItemsLivedata(): LiveData<List<MealsByCategoryListModel.Meal?>?>{
        return popularMealLiveData
    }

    fun observeCategoriesLivedata(): LiveData<List<CategoryListModel.Category?>?>{
        return categoriesLiveData
    }
}