package com.example.dogagecalculator.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dogagecalculator.R
import com.example.dogagecalculator.viewmodel.DogAgeCalculatorViewModel

@Composable
fun DogAgeCalculatorApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = stringResource(R.string.home)
    ) {
        composable(route = "Home" ) {
            MainScreen(navController, viewModel = DogAgeCalculatorViewModel())
        }
        composable(route = "Info" ) {
            InfoScreen(navController)
        }
        composable(route = "Chart" ) {
            ChartScreen(navController)
        }
    }
}