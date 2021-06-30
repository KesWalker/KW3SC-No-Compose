package kw.cube.pokemon.network

import kw.cube.pokemon.model.PokemonProfile
import kw.cube.pokemon.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * We use retrofit to access the pokemon backend
 * https://square.github.io/retrofit/
 */
interface PokemonService {

    @GET("pokemon")
    suspend fun search(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): SearchResponse

    @GET("pokemon/{id}/")
    suspend fun profile(
        @Path("id") id: String
    ): PokemonProfile
}