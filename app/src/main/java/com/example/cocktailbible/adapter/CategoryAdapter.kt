package com.example.cocktailbible.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailbible.R
import com.example.cocktailbible.network.data.CategoryList
import com.example.cocktailbible.network.data.CocktailResponse
import com.example.cocktailbible.network.data.Drinks
import com.example.cocktailbible.network.data.DrinksList
import kotlinx.android.synthetic.main.row_cocktail_category.view.*

class CategoryAdapter(val clickListener: ClickListener) : RecyclerView.Adapter<CategoryAdapter.CocktailViewHolder>() {
    var cocktailList: List<CocktailResponse.Drink> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        return CocktailViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_cocktail_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        return holder.bind(cocktailList[position])
    }

    inner class CocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cocktailCategory: CocktailResponse.Drink) {
            itemView.category_name.text = cocktailCategory.strCategory

        }
    }

    fun addData(cocktail: List<CocktailResponse.Drink>) {
        this.cocktailList = cocktail
    }

    override fun getItemCount(): Int {
        return cocktailList.size
    }

    interface ClickListener{
        fun onItemClicked(cocktail: List<CocktailResponse.Drink>)
    }
}