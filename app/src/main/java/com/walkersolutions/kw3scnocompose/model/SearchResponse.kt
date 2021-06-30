package kw.cube.pokemon.model

import kw.cube.pokemon.model.PokemonPreview

data class SearchResponse(
    var count: Int,
    var next: String,
    var previous: String,
    var results: List<PokemonPreview>
)