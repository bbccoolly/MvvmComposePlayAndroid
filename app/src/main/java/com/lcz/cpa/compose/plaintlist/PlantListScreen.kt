package com.lcz.cpa.compose.plaintlist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.lcz.core.model.sunflower.SunflowerPhotosEntity
import com.lcz.cpa.R

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-29
 */
@Composable
fun PlantListScreen(
    modifier: Modifier = Modifier,
    onPlantClick: (SunflowerPhotosEntity) -> Unit,
    viewModel: PlantListViewModel = hiltViewModel()
) {
    val plants by viewModel.plants.observeAsState(initial = emptyList())
    PlantListScreen(plants = plants, modifier, onPlantClick = onPlantClick)
}


@Composable
fun PlantListScreen(
    plants: List<SunflowerPhotosEntity>,
    modifier: Modifier = Modifier,
    onPlantClick: (SunflowerPhotosEntity) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.testTag("plant_list"),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.card_side_margin),
            vertical = dimensionResource(id = R.dimen.header_margin)
        )
    ) {
        items(items = plants, key = { it.id }) { plant ->
            PlantListItem(plant = plant) {
                onPlantClick(plant)
            }
        }
    }
}

//@Preview
//@Composable
//private fun PlantListScreenPreview(
//    @PreviewParameter(PlantListPreviewParamProvider::class) plants: List<SunflowerPhotosEntity>
//) {
//    PlantListScreen(plants = plants)
//}

//private class PlantListPreviewParamProvider : PreviewParameterProvider<List<SunFlowerPlantEntity>> {
//    override val values: Sequence<List<SunFlowerPlantEntity>> =
//        sequenceOf(
//            emptyList(),
//            listOf(
//                SunFlowerPlantEntity(
//                    "1",
//                    "Apple",
//                    "Apple",
//                    growZoneNumber = 1,
//                    imageUrl = "https://www.baidu.com/img/bd_logo1.png?where=super"
//                ),
//                SunFlowerPlantEntity(
//                    "2",
//                    "Banana",
//                    "Banana",
//                    growZoneNumber = 2,
//                    imageUrl = "https://images.unsplash.com/profile-1567498193578-91edac9cdf67image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128"
//                ),
//                SunFlowerPlantEntity(
//                    "3",
//                    "Carrot",
//                    "Carrot",
//                    growZoneNumber = 3,
//                    imageUrl = "https://images.unsplash.com/profile-1567498193578-91edac9cdf67image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128"
//                ),
//                SunFlowerPlantEntity(
//                    "4",
//                    "Dill",
//                    "Dill",
//                    growZoneNumber = 3,
//                    imageUrl = "https://images.unsplash.com/profile-1567498193578-91edac9cdf67image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128"
//                ),
//            )
//        )
//}