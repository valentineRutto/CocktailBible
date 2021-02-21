package com.example.cocktailbible.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailbible.adapter.CategoryAdapter
import com.example.cocktailbible.adapter.CocktailListAdapter
import com.example.cocktailbible.adapter.OnItemClickedListener
import com.example.cocktailbible.databinding.ActivityPopularCocktailsListBinding
import com.example.cocktailbible.network.CocktailsService
import com.example.cocktailbible.network.RetrofitClient
import com.example.cocktailbible.network.data.CategoryList
import com.example.cocktailbible.network.data.CocktailResponse
import com.example.cocktailbible.network.data.Drink
import com.example.cocktailbible.utils.Status
import com.example.cocktailbible.viewModel.MainViewModel
import com.example.cocktailbible.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class PopularCocktailsListActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityPopularCocktailsListBinding
    var cocktailCocktailListAdapter: CocktailListAdapter? = null
    var cocktailCategoryListAdapter: CategoryAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var popularCocktailList: MutableList<Drink>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopularCocktailsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Popular Cocktails"
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

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        val  layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.rvPopular.setHasFixedSize(false)
        binding.rvPopular.itemAnimator = DefaultItemAnimator()
        binding.rvPopular.layoutManager = layoutManager

        binding.rvCategory.setHasFixedSize(false)
        binding.rvCategory.itemAnimator = DefaultItemAnimator()
        binding.rvCategory.layoutManager = layoutManager2

        cocktailCocktailListAdapter =
            CocktailListAdapter(object : CocktailListAdapter.ClickListener {
                override fun onItemClicked(cocktail: List<CocktailResponse>) {
                    TODO("Not yet implemented")
                }
            })

        cocktailCategoryListAdapter =
            CategoryAdapter(object : OnItemClickedListener {

                override fun onItemSelected(item: CategoryList, position: Int) {
                    TODO("Not yet implemented")
                }
            })

        binding.rvPopular.adapter = cocktailCocktailListAdapter
        binding.rvCategory.adapter =cocktailCategoryListAdapter
    }

    fun setupObserver() {
        mainViewModel.getPopularCocktails().observe(this, Observer { popularCocktailList ->
            popularCocktailList?.let { resource ->

                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        cocktailCocktailListAdapter?.addData(resource.data!!.drinks)
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

        mainViewModel.getCocktailCategories().observe(this, Observer { cocktailCategoryList ->
            cocktailCategoryList?.let { resource ->

                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        cocktailCategoryListAdapter?.submitList(resource?.data?.drinksCategory)
                    }
                    Status.LOADING -> {
                        showProgress(true)
                    }
                    Status.ERROR -> {
                        showProgress(false)
                        Log.d("cocktailCategory", cocktailCategoryList.message)
                        Toast.makeText(this, cocktailCategoryList.message, Toast.LENGTH_LONG).show()
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