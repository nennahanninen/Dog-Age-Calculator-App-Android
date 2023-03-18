package com.example.dogagecalculator.view

import androidx.compose.foundation.layout.*
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
    val fact = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val factResponse = withContext(Dispatchers.IO) { FactApi.instance.getFact() }
            fact.value = factResponse.fact
        } catch (e: Exception) {
            errorMessage.value = "CHECK YOUR INTERNET CONNECTION!\n ${e.message}"
        } finally {
            isLoading.value = false
        }
    }

    if (isLoading.value) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally, ) {
            CircularProgressIndicator()
            Text(text = stringResource(R.string.fact_loading_text))
        }
    } else if (errorMessage.value.isNotEmpty()) {
        ErrorScreen(errorMessage.value)
    } else {
        FactDisplay(fact.value)
    }
}

@Composable
fun FactDisplay(fact: String) {
    Text(text = fact, style = MaterialTheme.typography.body1, textAlign = TextAlign.Center)
}