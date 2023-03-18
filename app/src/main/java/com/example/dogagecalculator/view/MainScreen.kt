package com.example.dogagecalculator.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dogagecalculator.R
import com.example.dogagecalculator.view.theme.Second
import com.example.dogagecalculator.viewmodel.DogAgeCalculatorViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController, viewModel: DogAgeCalculatorViewModel) {
    val age = viewModel.ageInput.toIntOrNull() ?: 0

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        coroutineScope.launch {
            scrollState.scrollTo(0)
        }
    }

    Scaffold (
        topBar = { MainTopBar(title = stringResource(R.string.app_name), navController )},
        content = { Column (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .verticalScroll(state = scrollState)
        ){
            Divider()
            Text(text = stringResource(R.string.app_title), textAlign = TextAlign.Center, style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, color = colorResource(
                R.color.third)
            )
            )
            InfoButton()
            AgeField(ageInput = viewModel.ageInput, viewModel = viewModel)
            DogSizeList(viewModel = viewModel, onClick = { viewModel.setDogSize(it) })
            Text(text = stringResource(R.string.result_text), style = TextStyle(fontSize = 18.sp, color = colorResource(
                R.color.third)
            )
            )
            Text(text = "${viewModel.result}", style = TextStyle(fontSize = 70.sp, color = Second))
            Calculation(age = age, size = viewModel.size, setResult = { viewModel.setCalculationResult(it) }, viewModel = DogAgeCalculatorViewModel())
            Divider()
            Text(text = stringResource(R.string.random_dog_fact), style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = colorResource(
                R.color.third)
            )
            )
            FactScreen()
        }},
    )
}