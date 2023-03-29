package com.lcz.core.model.sunflower

import java.util.*

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-29
 */
data class SunFlowPlantEntity(
    val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7, // how often the plant should be watered, in days
    val imageUrl: String = ""
){
    fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
        since > lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, wateringInterval) }

    override fun toString() = name
}
