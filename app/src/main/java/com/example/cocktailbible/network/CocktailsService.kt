package com.example.cocktailbible.network

import com.example.cocktailbible.network.data.CategoryList
import com.example.cocktailbible.network.data.DrinksList
import retrofit2.Call
import retrofit2.http.GET

interface CocktailsService {
//    @GET("/search.php")
//    fun searchCocktailOrIngredientsByName(
//        @Query("i") query: String?
//    ): Call<CocktailResponse?>?
//
//    @GET("/filter.php")
//    fun filterByCategory(
//        @Query("c") query: String? = null
//    ): Call<CocktailList>
//
//    @GET("/filter.php/c=")
//    fun searchCocktailByIngredient(
//        @Query("i") query: String?
//    ): Call<CocktailResponse?>?

    @GET("list.php?c=list")
    fun listCocktailCategories(): Call<CategoryList>

    @GET("search.php?f=a")
    fun listCocktailsByFirstName(): Call<DrinksList>

}

