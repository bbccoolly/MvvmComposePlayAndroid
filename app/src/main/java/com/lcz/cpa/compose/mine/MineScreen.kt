package com.lcz.cpa.compose.mine

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.lcz.cpa.R

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-28
 */
@Composable
fun MineScreen(
) {
    GalleryTopBar()
}


@Composable
private fun GalleryTopBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(stringResource(id = R.string.bottom_title_5_mine))
        },
        modifier = modifier.statusBarsPadding(),
        navigationIcon = {

        },
    )
}
