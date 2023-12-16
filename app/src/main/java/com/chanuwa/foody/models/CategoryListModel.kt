package com.chanuwa.foody.models


import com.google.gson.annotations.SerializedName

data class CategoryListModel(
    @SerializedName("meals")
    val meals: List<Meal?>?
) {
    data class Meal(
        @SerializedName("idMeal")
        val idMeal: String?,
        @SerializedName("strMeal")
        val strMeal: String?,
        @SerializedName("strMealThumb")
        val strMealThumb: String?
    )
}