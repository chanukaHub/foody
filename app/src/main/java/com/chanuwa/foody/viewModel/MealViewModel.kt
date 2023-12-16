package com.chanuwa.foody.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanuwa.foody.models.MealListModel
import com.chanuwa.foody.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(): ViewModel() {
    private var mealDetailsLiveData = MutableLiveData<MealListModel.Meal>()
    fun getMealDetail(id:String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealListModel>{
            override fun onResponse(call: Call<MealListModel>, response: Response<MealListModel>) {
                if (response.body()!= null){
                    mealDetailsLiveData.value = response.body()!!.meals!![0]
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealListModel>, t: Throwable) {
                Log.d("MealActivity", t.message.toString())
            }

        })
    }

    fun observerMealDetailsLiveData(): LiveData<MealListModel.Meal>{
        return mealDetailsLiveData
    }
}