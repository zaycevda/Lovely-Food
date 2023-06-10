package com.example.testforeffectivemobile.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testforeffectivemobile.R
import com.example.testforeffectivemobile.databinding.FragmentProductsBinding

class ProductsFragment : Fragment(R.layout.fragment_products) {

    private val binding by viewBinding(FragmentProductsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCategory.setOnClickListener {
            findNavController().navigate(R.id.action_productsFragment_to_productDialog)
        }
    }
}