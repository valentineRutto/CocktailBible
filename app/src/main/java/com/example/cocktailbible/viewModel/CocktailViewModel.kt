package com.example.cocktailbible.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cocktailbible.network.data.CategoryList
import com.example.cocktailbible.network.data.Drinks
import com.example.cocktailbible.network.data.DrinksList
import com.example.cocktailbible.repository.CocktailRepository

class CocktailViewModel(application: Application) : AndroidViewModel(application) {
    private var cocktailRepository = CocktailRepository()

//    private var categoryResponseLiveData: LiveData<List<CategoryList.Category>> = cocktailRepository.getCocktailCategory()
    private var cocktailListByFirstNameResponseLiveData: LiveData<List<Drinks>> = cocktailRepository.getCocktailList()

//    fun getCategoryResponseLiveData(): LiveData<List<CategoryList.Category>> {
//        return categoryResponseLiveData
//    }

    fun getCocktailListByFirstName(): LiveData<List<Drinks>>{
        return cocktailListByFirstNameResponseLiveData
    }
}