package com.example.cocktailbible.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cocktailbible.network.CocktailsService
import com.example.cocktailbible.network.RetrofitClient
import com.example.cocktailbible.network.data.CategoryList
import com.example.cocktailbible.network.data.CocktailResponse
import com.example.cocktailbible.network.data.Drinks
import com.example.cocktailbible.network.data.DrinksList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CocktailRepository {
    var cocktailsService: CocktailsService =
        RetrofitClient.getRetrofitService().create(CocktailsService::class.java)

    suspend fun fetchPopularCocktails(){
        val popularCocktailList: MutableLiveData<CocktailResponse> = MutableLiveData()



        withContext(Dispatchers.IO) {


            val response = cocktailsService.getPopularCocktails()



    }

    }

    fun getCocktailCategory(): LiveData<List<CategoryList.Category>> {
        val cocktailCategoryData: MutableLiveData<List<CategoryList.Category>> = MutableLiveData()
        cocktailsService.listCocktailCategories()
            .enqueue(object : Callback<CategoryList> {
                override fun onResponse(
                    call: Call<CategoryList>,
                    response: Response<CategoryList>
                ) {
                    Log.d(
                        "category",
                        "onResponse response:: $response"
                    )

                    if (response.body() != null) {
                        cocktailCategoryData.value = response.body()?.category
                        Log.d(
                            "category", "articles total result:: " + response.body()
                        )
                        Log.d(
                            "category", "articles size:: " + listOf(response.body()!!).size
                        )
                    }
                }

                override fun onFailure(
                    call: Call<CategoryList>,
                    t: Throwable
                ) {
                    cocktailCategoryData.value = null
                }
            })
        return cocktailCategoryData
    }

    fun getCocktailList(): LiveData<List<Drinks>> {
        val cocktailList: MutableLiveData<List<Drinks>> = MutableLiveData()

//        cocktailsService.listCocktailsByFirstName().enqueue(object : Callback<List<Drinks>> {
//            override fun onFailure(call: Call<List<Drinks>>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<List<Drinks>>, response: Response<List<Drinks>>) {
//                Log.d(
//                    "category",
//                    "onResponse response:: $response"
//                )
//
//                if (response.body() != null) {
//                    cocktailList.value = response.body()
//                    Log.d(
//                        "category", "drinks total result:: " + response.body()
//                    )
//                    Log.d(
//                        "category", "drinks size:: " + listOf(response.body()!!).size
//                    )
//                }
//            }
//
//        })
        return cocktailList
    }
}