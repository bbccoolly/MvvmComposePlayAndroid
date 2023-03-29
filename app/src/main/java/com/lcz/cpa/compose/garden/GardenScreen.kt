package com.lcz.cpa.compose.garden

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-29
 */
@Composable
fun GardenScreen(
    modifier: Modifier = Modifier,
) {
    GardenScreenS(
        modifier = modifier,
    )
}

@Composable
fun GardenScreenS(
    modifier: Modifier = Modifier
) {
    Text(text = "GardenScreen")
}