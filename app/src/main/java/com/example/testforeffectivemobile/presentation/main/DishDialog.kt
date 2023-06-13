package com.example.testforeffectivemobile.presentation.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.testforeffectivemobile.R
import com.example.testforeffectivemobile.databinding.DialogDishBinding

class DishDialog : DialogFragment(R.layout.dialog_dish) {

    private lateinit var binding: DialogDishBinding

    private val description by lazy { arguments?.getString(DESCRIPTION_KEY) }
    private val imageUrl by lazy { arguments?.getString(IMAGE_URL_KEY) }
    private val name by lazy { arguments?.getString(NAME_KEY) }
    private val price by lazy { arguments?.getInt(PRICE_KEY) }
    private val weight by lazy { arguments?.getInt(WEIGHT_KEY) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogDishBinding.inflate(layoutInflater)

        initDish()

        addPurchase()

        navigate()

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_dialog_dish)
    }

    private fun initDish() {
        binding.apply {
            tvDescription.text = description
            Glide.with(binding.root).load(imageUrl).into(ivDish)
            tvName.text = name
            tvPrice.text = getString(R.string.dish_price, price.toString())
            tvWeight.text = getString(R.string.dish_weight, weight.toString())
        }
    }

    private fun addPurchase() {
        binding.btnAddToCart.setOnClickListener {
            setFragmentResult(
                REQUEST_ACTION_KEY,
                bundleOf(ACTION_BUNDLE_KEY to true)
            )
            findNavController().navigateUp()
        }
    }

    private fun navigate() {
        binding.mcvClose.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object {
        const val DESCRIPTION_KEY = "DESCRIPTION_KEY"
        const val IMAGE_URL_KEY = "IMAGE_URL_KEY"
        const val NAME_KEY = "NAME_KEY"
        const val PRICE_KEY = "PRICE_KEY"
        const val WEIGHT_KEY = "WEIGHT_KEY"
        const val REQUEST_ACTION_KEY = "REQUEST_ACTION_KEY"
        const val ACTION_BUNDLE_KEY = "ACTION_BUNDLE_KEY"
    }
}