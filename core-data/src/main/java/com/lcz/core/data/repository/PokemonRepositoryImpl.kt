﻿package com.lcz.core.data.repository

import com.lcz.core.network.Dispatcher
import com.lcz.core.network.LczAppDispatchers
import com.lcz.core.network.service.PokemonDexClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-06
 */
class PokemonRepositoryImpl @Inject constructor(
    private val pokeDexClient: PokemonDexClient,
//    private val pokemonDao: PokemonDao,
    @Dispatcher(LczAppDispatchers.IO) private val ioDispatchers: CoroutineDispatcher

): PokemonRepository {
    override fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    )= flow {
        val response = pokeDexClient.fetchPokemonList(page = page)
        response.suspendOnSuccess {
            Timber.d("response count- ${data.count}")
            emit(data.results)
        }.onFailure {
            onError(message())
        }
//        var pokemons = pokemonDao.getPokemonList(page).asDomain()
//        if (pokemons.isEmpty()) {
//            val response = pokeDexClient.fetchPokemonList(page = page)
//            Timber.d("response 1- $response")
//            response.suspendOnSuccess {
//                pokemons = data.results
//                pokemons.forEach { pokemon -> pokemon.page = page }
//                pokemonDao.insertPokemonList(pokemons.asEntity())
//                emit(pokemonDao.getAllPokemonList(page).asDomain())
//            }.onFailure {
//                onError(message())
//            }
//        } else {
//            Timber.d("response 2- ")
//            emit(pokemonDao.getAllPokemonList(page).asDomain())
//        }

    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatchers)
}