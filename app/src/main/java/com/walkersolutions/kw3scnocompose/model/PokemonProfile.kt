package kw.cube.pokemon.model

import com.walkersolutions.kw3scnocompose.model.TitleText
import kw.cube.pokemon.util.imgHost

/**
 * Contains all the attributes of an individual pokemon. Although slightly less than the total
 * available attributes, I removed a number of ones I thought were unnecessary.
 * I used this plugin to help convert JSON strings into Kotlin classes:
 * https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-
 */
data class PokemonProfile(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
){

    fun getImgUrl(): String {
        return "$imgHost$id.png";
    }

    /**
     * Get a list of img urls that display the pokemon and different versions.
     */
    fun getSpritesList() : MutableList<String>{
        val sprites : MutableList<String?> = mutableListOf(
            sprites.front_default,
            sprites.back_default,
            sprites.front_shiny,
            sprites.back_shiny,
            sprites.front_female,
            sprites.back_female,
            sprites.front_shiny_female,
            sprites.back_shiny_female
        )
        return listOfNotNull(*sprites.toTypedArray()).toMutableList()
        // uses '*' spread operator to pass contents of array instead of the array itself
    }

    fun getTextInfoList(): List<TitleText> {
        return arrayListOf(
            TitleText("Species:",species.name),
            TitleText("Types:",types.joinToString{ it.type.name }),
            TitleText("Abilities:",abilities.joinToString { it.ability.name }),
            TitleText("Weight:",weight.toString()),
            TitleText("Forms:",forms.joinToString{ it.name })
        )
    }
}