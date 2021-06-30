package kw.cube.pokemon.repo

import kw.cube.pokemon.model.PokemonPreview
import kw.cube.pokemon.model.PokemonProfile
import kw.cube.pokemon.network.PokemonService
import javax.inject.Inject

/**
 * The repo is shared between view models to prevent duplicate code.
 */
class PokemonRepo @Inject constructor(private val pokemonService: PokemonService) {

    suspend fun search(offset: Int, limit: Int): List<PokemonPreview> {
        return pokemonService.search(offset = offset, limit = limit).results
    }

    suspend fun getProfile(id: String): PokemonProfile {
        return pokemonService.profile(id = id)
    }

}