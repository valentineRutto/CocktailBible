package com.example.cocktailbible.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailbible.R
import com.example.cocktailbible.adapter.CategoryAdapter
import com.example.cocktailbible.network.CocktailsService
import com.example.cocktailbible.network.RetrofitClient
import com.example.cocktailbible.network.data.CocktailResponse
import com.example.cocktailbible.utils.Status
import com.example.cocktailbible.viewModel.MainViewModel
import com.example.cocktailbible.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class PopularCocktailsListActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    var cocktailCategoryAdapter: CategoryAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var popularCocktailList: MutableList<CocktailResponse.Drink>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUi()
        setupObserver()

    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                RetrofitClient.getRetrofitService().create(CocktailsService::class.java)
            )
        ).get(MainViewModel::class.java)

    }

    fun setupUi() {

        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(false)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = layoutManager

        cocktailCategoryAdapter =
            CategoryAdapter(object : CategoryAdapter.ClickListener {
                override fun onItemClicked(cocktail: List<CocktailResponse.Drink>) {
                    TODO("Not yet implemented")
                }
            })

        recyclerView.adapter = cocktailCategoryAdapter
    }

    fun setupObserver() {
        mainViewModel.getPopularCocktails().observe(this, Observer {
            it?.let { resource ->

                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)


                        resource.data?.let { cocktails -> retrieveList(cocktails as MutableList<CocktailResponse.Drink>) }
                    }
                    Status.LOADING -> {
                        showProgress(true)

                    }
                    Status.ERROR -> {
                        showProgress(false)
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

    }

    private fun retrieveList(cocktail: MutableList<CocktailResponse.Drink>) {
        if (cocktail.isNotEmpty()) {
            popularCocktailList?.addAll(cocktail as MutableList<CocktailResponse.Drink>)
            cocktailCategoryAdapter?.addData(popularCocktailList  as List<CocktailResponse.Drink> )
            cocktailCategoryAdapter?.notifyDataSetChanged()
        }
    }

    private fun showProgress(status: Boolean) {
        if (status) {
            show_progress.visibility = View.VISIBLE
        } else {
            show_progress.visibility = View.GONE
        }
    }
}