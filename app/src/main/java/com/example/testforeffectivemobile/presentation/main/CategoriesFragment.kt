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
import com.example.testforeffectivemobile.databinding.FragmentCategoriesBinding
import com.example.testforeffectivemobile.presentation.main.adapters.CategoriesAdapter
import com.example.testforeffectivemobile.presentation.main.viewmodels.CategoriesViewModel
import com.example.testforeffectivemobile.presentation.main.viewmodels.CategoriesViewModelFactory
import javax.inject.Inject

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    @Inject
    lateinit var categoriesViewModelFactory: CategoriesViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, categoriesViewModelFactory)[CategoriesViewModel::class.java]
    }

    private val binding by viewBinding(FragmentCategoriesBinding::bind)

    private var adapter: CategoriesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inject()

        initAdapter()

        getCategories()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }

    private fun inject() {
        App.componet.injectCategoriesFragment(categoriesFragment = this)
    }

    private fun initAdapter() {
        adapter = CategoriesAdapter(
            onClick = { categoryName ->
                findNavController().navigate(
                    R.id.action_categoriesFragment_to_dishesFragment,
                    bundleOf(DishesFragment.CATEGORY_NAME_KEY to categoryName)
                )
            }
        )
        binding.rvCategories.overScrollMode = View.OVER_SCROLL_NEVER
        binding.rvCategories.adapter = adapter
    }

    private fun getCategories() {
        viewModel.getCategories()
        lifecycleScope.launchWhenCreated {
            viewModel.categories.collect { state ->
                state.on(
                    error = {
                        binding.pbCategories.isGone = true
                        binding.llCategories.isGone = false
                    },
                    loading = {
                        binding.pbCategories.isGone = false
                        binding.llCategories.isGone = true
                    },
                    success = { categories ->
                        binding.pbCategories.isGone = true
                        binding.llCategories.isGone = false

                        adapter?.submitList(categories)
                    }
                )
            }
        }
    }
}