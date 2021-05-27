package com.example.cocktailbible.network.data


import com.google.gson.annotations.SerializedName

data class CocktailRecipeResponse(
    @SerializedName("ingredients")
    val ingredients: List<Ingredient?>?
) {
    data class Ingredient(
        @SerializedName("idIngredient")
        val idIngredient: String?,
        @SerializedName("strABV")
        val strABV: String?,
        @SerializedName("strAlcohol")
        val strAlcohol: String?,
        @SerializedName("strDescription")
        val strDescription: String?,
        @SerializedName("strIngredient")
        val strIngredient: String?,
        @SerializedName("strType")
        val strType: String?
    )
}