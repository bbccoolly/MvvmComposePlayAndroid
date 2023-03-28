package com.lcz.core.network.service

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import com.lcz.core.model.pokmon.PokemonInfo
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-03
 */
class PokemonDexClient @Inject constructor(
    private val pokemonApiService: PokemonApiService
) {

    suspend fun fetchPokemonList(
        page: Int
    ) =
        pokemonApiService.fetchPokemonList(limit = PAGE_SIZE, offset = page * PAGING_SIZE)


    suspend fun fetchPokemonInfo(
        name: String
    ): ApiResponse<PokemonInfo> =
        pokemonApiService.fetchPokemonInfo(name = name)


    companion object {
        private const val PAGING_SIZE = 20
    }
}