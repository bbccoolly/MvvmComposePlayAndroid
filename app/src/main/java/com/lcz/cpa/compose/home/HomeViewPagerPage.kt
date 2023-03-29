package com.lcz.cpa.compose.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lcz.cpa.R

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-28
 */

enum class HomeViewPagerPage(
    @StringRes val titleResId: Int,
    @DrawableRes val drawableResId: Int
) {
    BOTTOM_HOME(R.string.bottom_title_1_home, R.drawable.menu_home),
    BOTTOM_PROJECT(R.string.bottom_title_2_project, R.drawable.menu_project),
    BOTTOM_SQUARE(R.string.bottom_title_3_square, R.drawable.menu_public),
    BOTTOM_WECHAT(R.string.bottom_title_4_wechat, R.drawable.menu_wechat),
    BOTTOM_MINE(R.string.bottom_title_5_mine, R.drawable.menu_mine),
}

@Composable
fun HomeViewPagerScreen(
    modifier: Modifier = Modifier,
    onPageChange: (HomeViewPagerPage) -> Unit = {},
    onAttached: (Toolbar) -> Unit = {},
) {
    val activity = (LocalContext.current as AppCompatActivity)

//    AndroidViewBinding()

}

@Composable
fun LczBottomNavigation(navController: NavController) {
    val items = listOf(
        HomeViewPagerPage.BOTTOM_HOME,
        HomeViewPagerPage.BOTTOM_PROJECT,
        HomeViewPagerPage.BOTTOM_SQUARE,
        HomeViewPagerPage.BOTTOM_WECHAT,
        HomeViewPagerPage.BOTTOM_MINE
    )

    BottomNavigation(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { page ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = page.drawableResId),
                        contentDescription = stringResource(id = page.titleResId)
                    )
                },
                label = { Text(text = stringResource(id = page.titleResId)) },
                selected = false,
                onClick = {
                    navController.navigate(page.name)
                }
            )
        }
    }
}