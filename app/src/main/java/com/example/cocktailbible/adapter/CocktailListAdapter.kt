package com.example.cocktailbible.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailbible.R
import com.example.cocktailbible.network.data.CocktailResponse
import com.example.cocktailbible.network.data.Drink
import kotlinx.android.synthetic.main.row_cocktail_category.view.*


class CocktailListAdapter(val clickListener: ClickListener) :
    RecyclerView.Adapter<CocktailListAdapter.CocktailViewHolder>() {
    var cocktailList: List<Drink> = emptyList()
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
        fun bind(cocktailCategory: Drink) {
            Glide.with(itemView).load(cocktailCategory.strDrinkThumb).into(itemView.img_cocktail)
            itemView.category_name.text = cocktailCategory.strDrink
        }
    }

    fun addData(cocktail: List<Drink>) {
        this.cocktailList = cocktail
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cocktailList.size
    }

    interface ClickListener {
        fun onItemClicked(cocktail: List<CocktailResponse>)
    }
}