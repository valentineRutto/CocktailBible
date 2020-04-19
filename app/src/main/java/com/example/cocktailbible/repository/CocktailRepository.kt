package com.example.cocktailbible.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cocktailbible.network.CocktailsService
import com.example.cocktailbible.network.RetrofitClient
import com.example.cocktailbible.network.data.CategoryList
import com.example.cocktailbible.network.data.Drinks
import com.example.cocktailbible.network.data.DrinksList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CocktailRepository {
    var cocktailsService: CocktailsService =
        RetrofitClient.getRetrofitService().create(CocktailsService::class.java)

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

        cocktailsService.listCocktailsByFirstName().enqueue(object : Callback<DrinksList> {
            override fun onFailure(call: Call<DrinksList>, t: Throwable) {

            }

            override fun onResponse(call: Call<DrinksList>, response: Response<DrinksList>) {
                Log.d(
                    "category",
                    "onResponse response:: $response"
                )

                if (response.body() != null) {
                    cocktailList.value = response.body()
                    Log.d(
                        "category", "drinks total result:: " + response.body()
                    )
                    Log.d(
                        "category", "drinks size:: " + listOf(response.body()!!).size
                    )
                }
            }

        })
        return cocktailList
    }
}