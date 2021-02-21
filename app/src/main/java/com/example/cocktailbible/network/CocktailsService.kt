package com.example.cocktailbible.network

import com.example.cocktailbible.network.data.*
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
    suspend fun listCocktailCategories(): CategoryList

    @GET("search.php?f=a")
    fun listCocktailsByFirstName(): Call<List<Drinks>>

    @GET("popular.php")
    suspend fun getPopularCocktails(): CocktailResponse


}

