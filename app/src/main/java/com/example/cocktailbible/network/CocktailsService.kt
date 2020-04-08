package com.example.cocktailbible.network

import com.example.cocktailbible.network.data.CategoryList
import com.example.cocktailbible.network.data.CocktailList
import com.example.cocktailbible.network.data.CocktailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsService {
    @GET("/search.php")
    fun searchCocktailOrIngredientsByName(
        @Query("i") query: String?
    ): Call<CocktailResponse?>?

    @GET("/filter.php")
    fun filterByCategory(
        @Query("c") query: String? = null
    ): Call<CocktailList>

    @GET("/filter.php")
    fun searchCocktailByIngredient(
        @Query("i") query: String?
    ): Call<CocktailResponse?>?

    @GET("list.php?c=list")
    fun listCocktailCategories(): Call<CategoryList>

}

