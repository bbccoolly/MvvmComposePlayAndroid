package com.lcz.cpa.compose.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.lcz.core.model.sunflower.SunFlowPlantEntity
import com.lcz.cpa.R
import com.lcz.cpa.databinding.HomeScreenBinding

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-28
 */

enum class SunFlowerPage(
    @StringRes val titleResId: Int,
    @DrawableRes val drawableResId: Int
) {
    MY_GARDEN(R.string.tab_title_my_garden, R.drawable.ic_tab_garden),
    PLANT_LIST(R.string.tab_title_plant_list, R.drawable.ic_tab_plant),
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onPlantClick: (SunFlowPlantEntity) -> Unit = {},
    onPageChange: (SunFlowerPage) -> Unit = {},
    onAttached: (Toolbar) -> Unit = {},
) {
    val activity = (LocalContext.current as AppCompatActivity)
    AndroidViewBinding(factory = HomeScreenBinding::inflate, modifier = modifier) {
        onAttached(toolbar)
        activity.setSupportActionBar(toolbar)
        composeView.setContent {
            HomePagerScreen(onPlantClick = onPlantClick, onPageChange = onPageChange)
        }

    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePagerScreen(
    onPlantClick: (SunFlowPlantEntity) -> Unit,
    onPageChange: (SunFlowerPage) -> Unit,
    modifier: Modifier = Modifier,
    pages: Array<SunFlowerPage> = SunFlowerPage.values()
) {
    val pageState = rememberPagerState()
    LaunchedEffect(pageState.currentPage) {
        onPageChange(pages[pageState.currentPage])
    }

    Text(text = "HomePagerScreen")
}