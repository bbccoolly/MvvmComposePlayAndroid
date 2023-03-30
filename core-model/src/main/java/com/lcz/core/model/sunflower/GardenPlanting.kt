package com.lcz.core.model.sunflower

import java.util.*

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-29
 */
data class GardenPlanting(
    val plantId: String,

    /**
     * Indicates when the [Plant] was planted. Used for showing notification when it's time
     * to harvest the plant.
     */
    val plantDate: Calendar = Calendar.getInstance(),

    /**
     * Indicates when the [Plant] was last watered. Used for showing notification when it's
     * time to water the plant.
     */

    val lastWateringDate: Calendar = Calendar.getInstance()
) {

    var gardenPlantingId: Long = 0
}

