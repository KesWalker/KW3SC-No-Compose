package com.walkersolutions.kw3scnocompose.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ProgressBar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.walkersolutions.kw3scnocompose.R
import com.walkersolutions.kw3scnocompose.ui.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint
import kw.cube.pokemon.model.PokemonProfile
import kw.cube.pokemon.ui.list.ListViewModel

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private val gridAdapter = PokemonGridAdapter(onPokemonSelect = { onPokemonSelect(it) })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        setupGrid(view)
        listenForData(view)

        return view;
    }

    private fun listenForData(view: View) {
        val spinner = view.findViewById<ProgressBar>(R.id.progress_spinner)
        spinner.visibility = VISIBLE
        viewModel.pokemons.observe(viewLifecycleOwner){
            gridAdapter.pokemonList = it
            gridAdapter.notifyDataSetChanged()
            spinner?.visibility = GONE
        }
        viewModel.error.observe(viewLifecycleOwner){ error ->
            if(error){
                spinner.visibility = GONE
                Snackbar.make(view,getString(R.string.error_has_occured), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupGrid(view: View) {
        val gridView = view.findViewById<GridView>(R.id.pokemon_grid)
        gridView.adapter = gridAdapter
    }

    private fun onPokemonSelect(id: String) {
        val frag = ProfileFragment()
        val bundle = frag.createBundle(id)
        frag.arguments = bundle
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .replace(R.id.fragment_host,frag)
            .addToBackStack(null)
            .commit()
    }

}