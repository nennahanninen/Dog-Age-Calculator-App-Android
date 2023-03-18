package com.example.dogagecalculator.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.dogagecalculator.R
import com.example.dogagecalculator.viewmodel.DogAgeCalculatorViewModel

@Composable
fun Calculation(age: Int, size: String, setResult:(Int) -> Unit, viewModel: DogAgeCalculatorViewModel) {
    val small = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
    val medium = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
    val large = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
    val giant = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
    var result = 0
    Button(
        onClick = {
            if (size == "Small" && age in small) {
                result = when (age) {
                    1 -> 15
                    2 -> 24
                    3 -> 28
                    4 -> 32
                    5 -> 36
                    6 -> 40
                    7 -> 44
                    8 -> 48
                    9 -> 52
                    10 -> 56
                    11 -> 60
                    12 -> 64
                    13 -> 68
                    14 -> 72
                    15 -> 76
                    16 -> 80
                    else -> 0
                }
            } else if (size == "Medium" && age in medium) {
                result = when (age) {
                    1 -> 15
                    2 -> 24
                    3 -> 28
                    4 -> 32
                    5 -> 36
                    6 -> 42
                    7 -> 47
                    8 -> 51
                    9 -> 56
                    10 -> 60
                    11 -> 65
                    12 -> 69
                    13 -> 74
                    14 -> 78
                    15 -> 83
                    16 -> 87
                    else -> 0
                }
            } else if (size == "Large" && age in large) {
                result = when (age) {
                    1 -> 15
                    2 -> 24
                    3 -> 28
                    4 -> 32
                    5 -> 36
                    6 -> 45
                    7 -> 50
                    8 -> 55
                    9 -> 61
                    10 -> 66
                    11 -> 72
                    12 -> 77
                    13 -> 82
                    14 -> 88
                    15 -> 93
                    16 -> 99
                    else -> 0
                }
            } else if (size == "Giant" && age in giant) {
                result = when (age) {
                    1 -> 15
                    2 -> 22
                    3 -> 31
                    4 -> 38
                    5 -> 45
                    6 -> 49
                    7 -> 56
                    8 -> 64
                    9 -> 71
                    10 -> 79
                    11 -> 86
                    12 -> 93
                    13 -> 100
                    14 -> 107
                    15 -> 114
                    16 -> 121
                    else -> 0
                }
            }
            viewModel.setCalculationResult(result)
            setResult(result)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.get_result))
    }
}
