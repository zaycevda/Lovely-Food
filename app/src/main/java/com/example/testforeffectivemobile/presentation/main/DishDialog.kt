package com.example.testforeffectivemobile.presentation.main

import androidx.fragment.app.DialogFragment
import com.example.testforeffectivemobile.R

class DishDialog : DialogFragment(R.layout.dialog_dish) {
    companion object {
        const val DISH_ID_KEY = "DISH_ID_KEY"
    }
}