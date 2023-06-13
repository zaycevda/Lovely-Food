package com.example.testforeffectivemobile.presentation.cart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.domain.models.Purchase
import com.example.testforeffectivemobile.R
import com.example.testforeffectivemobile.databinding.ItemPurchaseBinding

private typealias Action = (purchase: Purchase) -> Unit

class PurchasesAdapter(
    private val onClickMinus: Action,
    private val onClickPlus: Action
) : RecyclerView.Adapter<PurchasesAdapter.ViewHolder>() {

    private val differ = AsyncListDiffer(this, DiffUtilCallback())

    var purchases: List<Purchase>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding by viewBinding(ItemPurchaseBinding::bind)

        fun bind(purchase: Purchase) {
            binding.apply {
                tvCount.text = purchase.count.toString()
                Glide.with(root).load(purchase.imageUrl).into(ivPurchase)
                tvName.text = purchase.name
                tvPrice.text = root.resources.getString(R.string.dish_price, purchase.price.toString())
                tvWeight.text = root.resources.getString(R.string.dish_weight, purchase.weight.toString())

                ivMinus.setOnClickListener { onClickMinus(purchase) }
                ivPlus.setOnClickListener { onClickPlus(purchase) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_purchase, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(purchases[position])
    }

    override fun getItemCount() = purchases.size

    private class DiffUtilCallback : DiffUtil.ItemCallback<Purchase>() {
        override fun areItemsTheSame(oldItem: Purchase, newItem: Purchase) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Purchase, newItem: Purchase) = oldItem == newItem
    }
}