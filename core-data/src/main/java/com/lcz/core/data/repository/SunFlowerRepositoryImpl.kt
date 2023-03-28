package com.lcz.core.data.repository

import androidx.annotation.WorkerThread
import com.lcz.core.model.sunflower.SunflowerPhotosEntity
import com.lcz.core.network.Dispatcher
import com.lcz.core.network.LczAppDispatchers
import com.lcz.core.network.service.SunflowerDexClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-08
 */
class SunFlowerRepositoryImpl @Inject constructor(
    private val sunflowerDexClient: SunflowerDexClient,
    @Dispatcher(LczAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher

) : SunFlowerRepository {

    @WorkerThread
    override fun fetchSunFlowerPhotosInfo(
        searchKey: String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var photosEntityList: List<SunflowerPhotosEntity>
        val response = sunflowerDexClient.fetchSunflowerPhotos(searchKey = searchKey)
        response.suspendOnSuccess {
            photosEntityList = data.results
            emit(photosEntityList)
        }.onFailure {
            onError(message())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)


}