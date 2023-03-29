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
import com.lcz.cpa.compose.home.HomeViewPagerPage
import com.lcz.cpa.compose.home.HomeViewPagerScreen
import com.lcz.cpa.compose.mine.MineScreen
import com.lcz.cpa.compose.project.ProjectScreen
import com.lcz.cpa.compose.wechat.WechatScreen

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-28
 */

@Composable
fun NaviApp(
    onPageChange: (HomeViewPagerPage) -> Unit = {},
    onAttached: (Toolbar) -> Unit = {},
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        onPageChange = onPageChange,
        onAttached = onAttached
    )
}

@Composable
fun NavHost(
    navController: NavHostController,
    onPageChange: (HomeViewPagerPage) -> Unit = {},
    onAttached: (Toolbar) -> Unit = {},
) {
    val activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = HomeViewPagerPage.BOTTOM_HOME.name) {
        composable(HomeViewPagerPage.BOTTOM_HOME.name) {
            HomeScreen()
        }
        composable(HomeViewPagerPage.BOTTOM_PROJECT.name) {
            ProjectScreen()
        }
        composable(HomeViewPagerPage.BOTTOM_SQUARE.name) {
            ProjectScreen()
        }
        composable(HomeViewPagerPage.BOTTOM_WECHAT.name) {
            WechatScreen()
        }
        composable(HomeViewPagerPage.BOTTOM_MINE.name) {
            MineScreen()
        }
    }
}