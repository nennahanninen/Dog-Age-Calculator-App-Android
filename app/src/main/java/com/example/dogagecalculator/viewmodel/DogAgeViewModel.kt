package com.example.dogagecalculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DogAgeViewModel: ViewModel() {
    var ageInput by mutableStateOf("")

    fun changeAgeInput(value: String){
        ageInput = value
    }
}