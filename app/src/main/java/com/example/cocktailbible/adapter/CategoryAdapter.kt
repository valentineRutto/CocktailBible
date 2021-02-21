package com.example.cocktailbible.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailbible.databinding.RowTextBinding
import com.example.cocktailbible.network.data.CategoryList

object  CategoryDiffer : DiffUtil.ItemCallback<CategoryList.DrinksCategory>() {

    override fun areItemsTheSame(
        oldItem: CategoryList.DrinksCategory,
        newItem: CategoryList.DrinksCategory
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CategoryList.DrinksCategory,
        newItem: CategoryList.DrinksCategory
    ): Boolean {
        return oldItem == newItem
    }
}

interface OnItemClickedListener {
    fun onItemSelected(item: CategoryList, position: Int)
}

class CategoryAdapter(
    var listener: OnItemClickedListener
) : ListAdapter<CategoryList.DrinksCategory, CategoryAdapter.ViewHolder>(CategoryDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ViewHolder private constructor(
        private val binding: RowTextBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(categoryList:CategoryList.DrinksCategory , listener: OnItemClickedListener) {
            binding.txtCategoryName.text = categoryList?.strCategory
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = RowTextBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
