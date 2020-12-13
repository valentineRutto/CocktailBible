package com.example.cocktailbible.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cocktailbible.R
import com.example.cocktailbible.adapter.CategoryAdapter
import com.example.cocktailbible.network.CocktailsService
import com.example.cocktailbible.network.RetrofitClient
import com.example.cocktailbible.network.data.CocktailResponse
import com.example.cocktailbible.network.data.Drink
import com.example.cocktailbible.utils.Status
import com.example.cocktailbible.viewModel.MainViewModel
import com.example.cocktailbible.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class PopularCocktailsListActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    var cocktailCategoryAdapter: CategoryAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var popularCocktailList: MutableList<Drink>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Popular Cocktails"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
//        supportActionBar?.title = "Popular Cocktails"
        setSupportActionBar(toolbar)
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
        layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.setHasFixedSize(false)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = layoutManager

        cocktailCategoryAdapter =
            CategoryAdapter(object : CategoryAdapter.ClickListener {
                override fun onItemClicked(cocktail: List<CocktailResponse>) {
                    TODO("Not yet implemented")
                }
            })

        recyclerView.adapter = cocktailCategoryAdapter
    }

    fun setupObserver() {
        mainViewModel.getPopularCocktails().observe(this, Observer { popularCocktailList ->
            popularCocktailList?.let { resource ->

                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        cocktailCategoryAdapter?.addData(resource.data!!.drinks)
                    }
                    Status.LOADING -> {
                        showProgress(true)

                    }
                    Status.ERROR -> {
                        showProgress(false)
                        Log.d("cocktail", popularCocktailList.message)
                        Toast.makeText(this, popularCocktailList.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

    }

    private fun showProgress(status: Boolean) {
        if (status) {
            show_progress.visibility = View.VISIBLE
        } else {
            show_progress.visibility = View.GONE
        }
    }
}