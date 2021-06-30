package kw.cube.pokemon.model

import kw.cube.pokemon.util.imgHost

data class PokemonPreview(
    var name: String,
    var url: String
) {
    fun getId(): String {
        return url.dropLast(1).takeLastWhile { it.isDigit() }
    }

    fun getImgUrl(): String {
        return imgHost + getId() + ".png";
    }
}