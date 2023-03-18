package com.example.dogagecalculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DogAgeCalculatorViewModel: ViewModel() {
    var ageInput by mutableStateOf("")
    var size by mutableStateOf("")
    var result by mutableStateOf(0)

    fun setAge(input: String) {
        ageInput = input
    }

    fun setDogSize(selectedText: String) {
        size = when (selectedText) {
            "Small" -> "Small"
            "Medium" -> "Medium"
            "Large" -> "Large"
            "Giant" -> "Giant"
            else -> "Medium"
        }
    }

    fun setCalculationResult(result: Int) {
        this.result = result
    }
}