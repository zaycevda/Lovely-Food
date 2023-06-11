package com.example.testforeffectivemobile.presentation.main

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testforeffectivemobile.App
import com.example.testforeffectivemobile.R
import com.example.testforeffectivemobile.databinding.FragmentDishesBinding
import com.example.testforeffectivemobile.presentation.main.adapters.DishesAdapter
import com.example.testforeffectivemobile.presentation.main.adapters.SpacingDishDecoration
import com.example.testforeffectivemobile.presentation.main.viewmodels.DishesViewModel
import com.example.testforeffectivemobile.presentation.main.viewmodels.DishesViewModelFactory
import javax.inject.Inject

class DishesFragment : Fragment(R.layout.fragment_dishes) {

    @Inject
    lateinit var dishesViewModelFactory: DishesViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, dishesViewModelFactory)[DishesViewModel::class.java]
    }

    private val binding by viewBinding(FragmentDishesBinding::bind)

    private var adapter: DishesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inject()

        initAdapter()

        initToolbar()

        getDishes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }

    private fun inject() {
        App.componet.injectDishesFragment(dishesFragment = this)
    }

    private fun initAdapter() {
        adapter = DishesAdapter(
            onClick = { dish ->
                findNavController().navigate(
                    R.id.action_dishesFragment_to_dishDialog,
                    bundleOf(
                        DishDialog.DESCRIPTION_KEY to dish.description,
                        DishDialog.IMAGE_URL_KEY to dish.imageUrl,
                        DishDialog.NAME_KEY to dish.name,
                        DishDialog.PRICE_KEY to dish.price,
                        DishDialog.WEIGHT_KEY to dish.weight
                    )
                )
            }
        )

        val itemSpacing = resources.getDimensionPixelSize(R.dimen.item_spacing)
        val lineSpacing = resources.getDimensionPixelSize(R.dimen.line_spacing)
        val itemDecoration = SpacingDishDecoration(
            SPAN_COUNT,
            itemSpacing,
            lineSpacing,
            INCLUDE_EDGE
        )

        binding.rvDishes.overScrollMode = View.OVER_SCROLL_NEVER
        binding.rvDishes.adapter = adapter
        binding.rvDishes.addItemDecoration(itemDecoration)
    }

    private fun initToolbar() {
        val categoryName = arguments?.getString(CATEGORY_NAME_KEY)
        binding.tvCategory.text = categoryName

        binding.tbDishes.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun getDishes() {
        viewModel.getDishes()
        lifecycleScope.launchWhenCreated {
            viewModel.dishes.collect { state ->
                state.on(
                    error = {
                        binding.pbCategories.isGone = true
                        binding.llDishes.isGone = false
                    },
                    loading = {
                        binding.pbCategories.isGone = false
                        binding.llDishes.isGone = true
                    },
                    success = { dishes ->
                        binding.pbCategories.isGone = true
                        binding.llDishes.isGone = false

                        dishes?.let { adapter?.dishes = it }
                    }
                )
            }
        }
    }

    companion object {
        const val CATEGORY_NAME_KEY = "CATEGORY_NAME_KEY"
        private const val SPAN_COUNT = 3
        private const val INCLUDE_EDGE = true
    }
}