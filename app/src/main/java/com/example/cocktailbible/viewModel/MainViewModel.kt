package com.example.cocktailbible.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cocktailbible.network.CocktailsService
import com.example.cocktailbible.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val cocktailsService: CocktailsService) : ViewModel(){

    fun getPopularCocktails() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data= cocktailsService.getPopularCocktails()))
        }catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message?:"Unable to fetch cocktails"))
        }
    }
}