package com.lcz.cpa.compose.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.google.accompanist.themeadapter.material.MdcTheme
import com.lcz.core.model.sunflower.SunflowerPhotosEntity
import com.lcz.cpa.R
import com.lcz.cpa.compose.garden.GardenScreen
import com.lcz.cpa.compose.plaintlist.PlantListScreen
import com.lcz.cpa.databinding.HomeScreenBinding
import kotlinx.coroutines.launch

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-28
 */

enum class SunFlowerPage(
    @StringRes val titleResId: Int, @DrawableRes val drawableResId: Int
) {
    MY_GARDEN(
        R.string.tab_title_my_garden, R.drawable.ic_tab_garden
    ),
    PLANT_LIST(R.string.tab_title_plant_list, R.drawable.ic_tab_plant),
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onPlantClick: (SunflowerPhotosEntity) -> Unit = {},
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
    onPlantClick: (SunflowerPhotosEntity) -> Unit,
    onPageChange: (SunFlowerPage) -> Unit,
    modifier: Modifier = Modifier,
    pages: Array<SunFlowerPage> = SunFlowerPage.values()
) {
    val pagerState = rememberPagerState()
    LaunchedEffect(pagerState.currentPage) {
        onPageChange(pages[pagerState.currentPage])
    }

    Column(modifier.nestedScroll(rememberNestedScrollInteropConnection())) {
        val coroutineScope = rememberCoroutineScope()

        TabRow(selectedTabIndex = pagerState.currentPage) {
            pages.forEachIndexed { index, page ->
                val title = stringResource(id = page.titleResId)
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = title) },
                    icon = {
                        Icon(
                            painter = painterResource(id = page.drawableResId),
                            contentDescription = title
                        )
                    },
                    unselectedContentColor = MaterialTheme.colors.primaryVariant,
                    selectedContentColor = MaterialTheme.colors.secondary
                )

            }
        }

        //pages
        HorizontalPager(
            pageCount = pages.size, state = pagerState, verticalAlignment = Alignment.Top
        ) { page ->
            when (pages[page]) {
                SunFlowerPage.MY_GARDEN -> {
                    GardenScreen(
                        Modifier.fillMaxSize(), onAddPlantClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    SunFlowerPage.PLANT_LIST.ordinal
                                )
                            }
                        }, onPlantClick = {
                            onPlantClick(it.plant)
                        }
                    )
                }
                SunFlowerPage.PLANT_LIST -> {
                    PlantListScreen(onPlantClick = onPlantClick, modifier = Modifier.fillMaxSize())
                }
            }

        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeTopAppBar(
    pagerState: PagerState, onFilterClick: () -> Unit, modifier: Modifier = Modifier
) {
    TopAppBar(title = {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        }
    }, modifier.statusBarsPadding(), actions = {
        if (pagerState.currentPage == SunFlowerPage.PLANT_LIST.ordinal) {
            IconButton(onClick = onFilterClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter_list_24dp),
                    contentDescription = stringResource(
                        id = R.string.menu_filter_by_grow_zone
                    ),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    }, elevation = 0.dp
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MdcTheme {
        HomePagerScreen(onPlantClick = {}, onPageChange = {})
    }
}