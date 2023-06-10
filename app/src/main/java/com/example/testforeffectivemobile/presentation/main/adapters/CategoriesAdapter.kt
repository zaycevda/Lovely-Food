package com.example.testforeffectivemobile.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.domain.models.Category
import com.example.testforeffectivemobile.R
import com.example.testforeffectivemobile.databinding.ItemCategoryBinding

class CategoriesAdapter(
    private val onClick: (categoryName: String) -> Unit
) : ListAdapter<Category, CategoriesViewHolder>(CategoriesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoriesViewHolder.create(parent)

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
        holder.itemView.setOnClickListener {
            onClick(category.name)
        }
    }
}

class CategoriesDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Category, newItem: Category) = oldItem == newItem
}

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding by viewBinding(ItemCategoryBinding::bind)

    var category: Category? = null
        private set

    fun bind(category: Category) {
        this.category = category
        Glide.with(binding.root).load(category.imageUrl).into(binding.ivCategory)
        binding.tvCategory.text = category.name
    }

    companion object {
        fun create(parent: ViewGroup) =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_category, parent, false)
                .let(::CategoriesViewHolder)
    }
}