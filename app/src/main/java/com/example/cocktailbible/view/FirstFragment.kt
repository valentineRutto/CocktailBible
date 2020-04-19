package com.example.cocktailbible.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailbible.R
import com.example.cocktailbible.adapter.CategoryAdapter
import com.example.cocktailbible.viewModel.CocktailViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    var cocktailCategoryAdapter: CategoryAdapter? = null
    private lateinit var recyclerView: RecyclerView
    lateinit var cocktailViewModel: CocktailViewModel
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.category_rv)
        layoutManager = LinearLayoutManager(requireActivity())
        cocktailCategoryAdapter = CategoryAdapter()
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = cocktailCategoryAdapter

        cocktailViewModel = ViewModelProvider(requireActivity()).get(CocktailViewModel::class.java)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            getCategory()
        }
    }

    private fun getCategory() {
        cocktailViewModel.getCocktailListByFirstName()
            .observe(viewLifecycleOwner, Observer { cocktailsCategoryList ->
                if (cocktailsCategoryList != null) {
                    cocktailCategoryAdapter?.addData(cocktailsCategoryList)
                    cocktailCategoryAdapter?.notifyDataSetChanged()
                }
            })
    }
}
