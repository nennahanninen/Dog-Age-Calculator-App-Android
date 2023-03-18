package com.example.dogagecalculator.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.toSize
import com.example.dogagecalculator.R
import com.example.dogagecalculator.viewmodel.DogAgeCalculatorViewModel

@Composable
fun DogSizeList(viewModel: DogAgeCalculatorViewModel, onClick: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf( Size.Zero ) }
    val items = listOf("Small", "Medium", "Large", "Giant")

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {
        OutlinedTextField(
            readOnly = true,
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text = stringResource(R.string.select_dog_size)) },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            items.forEach{ label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    viewModel.setDogSize(label)
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

/*
fun DogSizeList(onClick: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf( Size.Zero ) }
    val items = listOf("Small", "Medium", "Large", "Giant")

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {
        OutlinedTextField(
            readOnly = true,
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text = stringResource(R.string.select_dog_size)) },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            items.forEach{ label ->
                DropdownMenuItem(onClick = {
                    selectedText = label

                    val dogSizeSelected: String = when (label) {
                        "Small" -> "Small"
                        "Medium" -> "Medium"
                        "Large" -> "Large"
                        "Giant" -> "Giant"
                        else -> "Medium"
                    }
                    onClick(dogSizeSelected)
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}
* */