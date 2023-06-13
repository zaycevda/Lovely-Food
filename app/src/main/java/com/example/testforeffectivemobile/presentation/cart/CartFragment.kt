package com.example.testforeffectivemobile.presentation.cart

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.models.Purchase
import com.example.testforeffectivemobile.App
import com.example.testforeffectivemobile.R
import com.example.testforeffectivemobile.databinding.FragmentCartBinding
import com.example.testforeffectivemobile.presentation.cart.adapters.PurchasesAdapter
import com.example.testforeffectivemobile.presentation.cart.viewmodels.CartViewModel
import com.example.testforeffectivemobile.presentation.cart.viewmodels.CartViewModelFactory
import javax.inject.Inject

class CartFragment : Fragment(R.layout.fragment_cart) {

    @Inject
    lateinit var cartViewModelFactory: CartViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, cartViewModelFactory)[CartViewModel::class.java]
    }

    private val binding by viewBinding(FragmentCartBinding::bind)

    private var adapter: PurchasesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inject()

        initAdapter()

        getPurchases()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }

    private fun inject() {
        App.componet.injectCartFragment(cartFragment = this)
    }

    private fun initAdapter() {
        adapter = PurchasesAdapter(
            onClickMinus = { purchase ->
                if (purchase.count == 1) deletePurchase(purchase.id)
                else viewModel.addPurchase(
                    Purchase(
                        id = purchase.id,
                        count = purchase.count - 1,
                        imageUrl = purchase.imageUrl,
                        name = purchase.name,
                        price = purchase.price,
                        weight = purchase.weight
                    )
                )

            },
            onClickPlus = { purchase ->
                viewModel.addPurchase(
                    Purchase(
                        id = purchase.id,
                        count = purchase.count + 1,
                        imageUrl = purchase.imageUrl,
                        name = purchase.name,
                        price = purchase.price,
                        weight = purchase.weight
                    )
                )
            },
        )
        binding.rvPurchase.overScrollMode = View.OVER_SCROLL_NEVER
        binding.rvPurchase.adapter = adapter
    }

    private fun deletePurchase(id: Long) {
        viewModel.deletePurchase(id)
    }

    private fun getPurchases() {
        viewModel.getPurchases()
        lifecycleScope.launchWhenCreated {
            viewModel.purchases.collect { state ->
                state.on(
                    error = {
                        binding.pbCart.isGone = true
                        binding.llCart.isGone = false
                    },
                    loading = {
                        binding.pbCart.isGone = false
                        binding.llCart.isGone = true
                    },
                    success = { purchases ->
                        binding.pbCart.isGone = true
                        binding.llCart.isGone = false

                        adapter?.purchases = purchases
                    }
                )
            }
        }
    }
}