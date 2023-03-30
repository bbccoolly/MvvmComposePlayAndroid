package com.lcz.cpa.compose.plaintlist

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.lcz.core.model.sunflower.SunflowerPhotosEntity
import com.lcz.cpa.R
import com.lcz.cpa.ui.theme.card

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-30
 */
@Composable
fun PlantListItem(plant: SunflowerPhotosEntity, onClick: () -> Unit) {
    ImageListItem(name = plant.user.name, imageUrl = plant.urls.small, onClick = onClick)
}

@Composable
fun PhotoListItem(photo: SunflowerPhotosEntity, onClick: () -> Unit) {
    ImageListItem(name = photo.user.name, imageUrl = photo.urls.small, onClick = onClick)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageListItem(name: String, imageUrl: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        elevation = dimensionResource(id = R.dimen.card_elevation),
        shape = MaterialTheme.shapes.card,
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
            .padding(bottom = dimensionResource(id = R.dimen.card_bottom_margin))
    ) {
        Column(Modifier.fillMaxSize()) {
            SunflowerImage(
                model = imageUrl,
                contentDescription = stringResource(R.string.a11y_plant_item_image),
                Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.plant_item_image_height)),
                contentScale = ContentScale.Crop
            )

            Text(
                text = name,
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.margin_normal))
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }


    }
}

@Preview
@Composable
fun ImageListItemPreview() {
    ImageListItem("sss", "SSS", {})
}