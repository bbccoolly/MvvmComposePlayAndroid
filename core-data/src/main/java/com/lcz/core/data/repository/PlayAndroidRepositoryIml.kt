package com.lcz.core.data.repository

import com.lcz.core.model.playandroid.HomeArticleEntity
import com.lcz.core.network.Dispatcher
import com.lcz.core.network.LczAppDispatchers
import com.lcz.core.network.service.PlayAndroidDexClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-11
 */
class PlayAndroidRepositoryIml @Inject constructor(
    private val playAndroidDexClient: PlayAndroidDexClient,
    @Dispatcher(LczAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : PlayAndroidRepository {
    override fun getHomeBannerInfo(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = playAndroidDexClient.getHomeBannerInfo()
        response.suspendOnSuccess {
            emit(data.data)
        }.onFailure {
            onError(message())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    override fun getHomeArticleList(
        pageNo: Int,
        pageSize: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = playAndroidDexClient.getHomeArticlePageList(pageNo, pageSize)
        response.suspendOnSuccess {
            emit(data.data)
        }.onFailure {
            onError(message())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    override fun getHomeArticleTopList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<HomeArticleEntity> {
        TODO("Not yet implemented")
    }

    override fun getHomeSquarePageList(
        pageNo: Int,
        pageSize: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = playAndroidDexClient.getHomeSquarePageList(pageNo, pageSize)
        response.suspendOnSuccess {
            emit(data.data)
        }.onFailure {
            onError(message())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    override fun getHomeAnswerPageList(
        pageNo: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = playAndroidDexClient.getHomeAnswerPageList(pageNo)
        response.suspendOnSuccess {
            emit(data.data)
        }.onFailure {
            onError(message())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}