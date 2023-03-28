﻿package com.lcz.core.data.repository

import androidx.annotation.WorkerThread
import com.lcz.core.model.pokmon.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-06
 */
interface PokemonRepository {
    @WorkerThread
    fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Pokemon>>
}