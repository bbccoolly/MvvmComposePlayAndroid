package com.lcz.cpa

import android.app.Activity
import androidx.appcompat.widget.Toolbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lcz.cpa.compose.home.HomeScreen
import com.lcz.cpa.compose.home.SunFlowerPage

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-28
 */

@Composable
fun SunflowerApp(
    onPageChange: (SunFlowerPage) -> Unit = {},
    onAttached: (Toolbar) -> Unit = {},
) {
    val navController = rememberNavController()
    SunFlowerNavHost(
        navController = navController,
        onPageChange = onPageChange,
        onAttached = onAttached
    )
}

@Composable
fun SunFlowerNavHost(
    navController: NavHostController,
    onPageChange: (SunFlowerPage) -> Unit = {},
    onAttached: (Toolbar) -> Unit = {},
) {
    val  activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onPlantClick = {
//                    navController.navigate("plantDetail/${it.id}")
                },
                onPageChange = onPageChange,
                onAttached = onAttached
            )
        }
    }
}