package com.lcz.core.data.repository

import androidx.annotation.WorkerThread
import com.lcz.core.model.sunflower.SunflowerPhotosEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-08
 */
interface SunFlowerRepository {

    @WorkerThread
    fun fetchSunFlowerPhotosInfo(
        searchKey: String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<SunflowerPhotosEntity>>
}