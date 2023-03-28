package com.lcz.core.network.model

import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

/**
 *
 * desc: A mapper for mapping [ApiResponse.Failure.Error] response as custom [PokemonErrorResponse] instance.
 * @see [ApiErrorModelMapper](https://github.com/skydoves/sandwich#apierrormodelmapper)
 * create by lcz on 2023-03-02
 */
object ErrorResponseMapper : ApiErrorModelMapper<PokemonErrorResponse> {

    /**
     * maps the [ApiResponse.Failure.Error] to the [PokemonErrorResponse] using the mapper.
     *
     * @param apiErrorResponse The [ApiResponse.Failure.Error] error response from the network request.
     * @return A customized [PokemonErrorResponse] error response.
     */
    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): PokemonErrorResponse {
        return PokemonErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }

}