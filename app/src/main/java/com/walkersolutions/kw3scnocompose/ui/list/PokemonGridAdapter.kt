package com.walkersolutions.kw3scnocompose.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.walkersolutions.kw3scnocompose.R
import kw.cube.pokemon.model.PokemonPreview

class PokemonGridAdapter(
    private var onPokemonSelect: ((String) -> Unit)
) : BaseAdapter() {

    var pokemonList: List<PokemonPreview> = mutableListOf()

    override fun getCount(): Int {
        return pokemonList.size
    }

    override fun getItem(position: Int): Any {
        return pokemonList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val pokemon = pokemonList[position]
        var view = convertView

        if(view == null){
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_pokemon_square,parent,false)!!
        }

        val pokemonImg = view.findViewById<ImageView>(R.id.pokemon_img)
        val pokemonTxt = view.findViewById<TextView>(R.id.pokemon_txt)

        Picasso.get().load(pokemon.getImgUrl())
            .placeholder(R.drawable.unknown)
            .priority(Picasso.Priority.HIGH)
            .into(pokemonImg)

        pokemonTxt.text = pokemon.name

        view.setOnClickListener { onPokemonSelect.invoke(pokemon.getId()) }

        return view
    }
}