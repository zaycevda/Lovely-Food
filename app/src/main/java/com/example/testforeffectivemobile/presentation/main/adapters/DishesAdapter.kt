package com.example.testforeffectivemobile.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.domain.models.Dish
import com.example.testforeffectivemobile.R
import com.example.testforeffectivemobile.databinding.ItemDishBinding

class DishesAdapter(
    private val onClick: (dish: Dish) -> Unit
) : RecyclerView.Adapter<DishesAdapter.ViewHolder>() {

    private val differ = AsyncListDiffer(this, DiffUtilCallback())

    var dishes: List<Dish>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding by viewBinding(ItemDishBinding::bind)

        fun bind(dish: Dish) {
            Glide.with(binding.root).load(dish.imageUrl).into(binding.ivDish)
            binding.tvDish.text = dish.name

            itemView.setOnClickListener {
                onClick(dish)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_dish, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dishes[position])
    }

    override fun getItemCount() = dishes.size

    private class DiffUtilCallback : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Dish, newItem: Dish) = oldItem == newItem
    }
}