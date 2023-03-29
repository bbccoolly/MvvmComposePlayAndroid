package com.lcz.cpa.compose.plaintlist

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-29
 */
@Composable
fun PlantListScreen(modifier: Modifier = Modifier) {
    PlantListScreenS(modifier = modifier)
}

@Composable
fun PlantListScreenS(modifier: Modifier) {
    Text(text = "PlantListScreen")
}