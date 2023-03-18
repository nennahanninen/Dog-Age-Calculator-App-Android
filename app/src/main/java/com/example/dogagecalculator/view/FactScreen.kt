package com.example.dogagecalculator.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dogagecalculator.R
import com.example.dogagecalculator.model.FactApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun FactScreen() {
    var fact by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        val factResponse = withContext(Dispatchers.IO) { FactApi.instance.getFact() }
        fact = factResponse.fact
        isLoading = false
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)) {
        if (isLoading) {
            CircularProgressIndicator()
            Text(text = stringResource(R.string.fact_loading_text))
        } else {
            Text(
                text = fact,
                style = MaterialTheme.typography.body1, textAlign = TextAlign.Center
            )
        }
    }
}