package com.example.dogagecalculator.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dogagecalculator.R

@Composable
fun InfoButton() {
    var dialogVisible by remember { mutableStateOf(false) }
    IconButton(onClick = { dialogVisible = true }) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = "Info Icon",
            tint = colorResource(R.color.third)
        )
    }

    if (dialogVisible) {
        AlertDialog(
            onDismissRequest = { dialogVisible = false },
            title = { Text(text = stringResource(R.string.info_icon_title), style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, color = colorResource(
                R.color.third)
            )
            ) },
            text = {
                Column {
                    Text(text = stringResource(R.string.age_range_title), fontWeight = FontWeight.Bold)
                    Text(text = stringResource(R.string.age_range))
                    Text(text = stringResource(R.string.size_title), fontWeight = FontWeight.Bold)
                    Text(text = stringResource(R.string.size_range_small))
                    Text(text = stringResource(R.string.size_range_medium))
                    Text(text = stringResource(R.string.size_range_large))
                    Text(text = stringResource(R.string.size_range_giant))
                }
            },
            confirmButton = {
                Button(
                    onClick = { dialogVisible = false },
                ) {
                    Text(text = stringResource(R.string.info_close_button))
                }
            }
        )
    }
}