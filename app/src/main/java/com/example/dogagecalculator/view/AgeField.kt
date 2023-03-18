package com.example.dogagecalculator.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.dogagecalculator.R
import com.example.dogagecalculator.viewmodel.DogAgeCalculatorViewModel

@Composable
fun AgeField(ageInput: String, viewModel: DogAgeCalculatorViewModel) {
    OutlinedTextField(
        value = ageInput,
        onValueChange = {
            viewModel.setAge(it)
        },
        label = { Text(text = stringResource(R.string.enter_dog_age)) },
        singleLine= true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}