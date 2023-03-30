package com.lcz.core.model.sunflower

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-29
 */
data class PlantAndGardenPlantings(
    val plant: SunFlowerPlantEntity,

    val gardenPlantings: List<GardenPlanting> = emptyList()
)
