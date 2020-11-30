package com.example.cocktailbible.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cocktailbible.network.CocktailsService
import java.lang.IllegalArgumentException

class ViewModelFactory(private val cocktailsService: CocktailsService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(cocktailsService) as T
        }
        throw IllegalArgumentException("Unknow class Name")
    }
}