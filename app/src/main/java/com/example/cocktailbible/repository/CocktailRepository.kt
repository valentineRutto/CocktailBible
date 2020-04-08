package com.example.cocktailbible.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cocktailbible.network.CocktailsService
import com.example.cocktailbible.network.RetrofitClient
import com.example.cocktailbible.network.data.CategoryList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CocktailRepository {
    var cocktailsService: CocktailsService =
        RetrofitClient.getRetrofitService().create(CocktailsService::class.java)

    fun getCocktailCategory(): LiveData<List<CategoryList.Category>> {
        val data: MutableLiveData<List<CategoryList.Category>> = MutableLiveData()
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
                        data.value = response.body()?.category
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
                    data.value = null
                }
            })
        return data
    }
}