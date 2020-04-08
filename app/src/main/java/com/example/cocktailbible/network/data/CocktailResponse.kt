package com.example.cocktailbible.network.data

data class CocktailResponse(
    val strDrink:String,
    val strDrinkThumb:String,
    val idDrink:String
)

data class CocktailList(
    val cocktailList: List<CocktailResponse>
)