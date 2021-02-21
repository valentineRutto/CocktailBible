package com.example.cocktailbible.network.data

import com.google.gson.annotations.SerializedName

data class CategoryList (

    @SerializedName("drinks")
    val drinksCategory: List<DrinksCategory?>
){
    data class DrinksCategory (
            @SerializedName("strCategory")
            val strCategory: String
        )
}


