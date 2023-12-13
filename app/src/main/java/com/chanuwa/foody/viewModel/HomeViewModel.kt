package com.chanuwa.foody.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanuwa.foody.models.MealListModel
import com.chanuwa.foody.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel() {
    private var randomMealLiveData = MutableLiveData<MealListModel.Meal>()

    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealListModel> {
            override fun onResponse(call: Call<MealListModel>, response: Response<MealListModel>) {
                if (response.body() != null) {
                    val meal: MealListModel.Meal? = response.body()!!.meals?.get(0)
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

    fun observeRandomMealLivedata(): LiveData<MealListModel.Meal>{
        return randomMealLiveData
    }
}