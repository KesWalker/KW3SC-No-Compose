package com.walkersolutions.kw3scnocompose.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.walkersolutions.kw3scnocompose.R
import dagger.hilt.android.AndroidEntryPoint
import kw.cube.pokemon.model.PokemonProfile
import kw.cube.pokemon.ui.list.ListViewModel
import kw.cube.pokemon.ui.list.ProfileViewModel
import kw.cube.pokemon.util.TAG

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()
    private val ID_ARG = "idArg"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val id = arguments?.getString(ID_ARG)
        if(id != null){
            viewModel.getProfile(id)
            listenForData(view)
        }else{
            Snackbar.make(view,getString(R.string.error_has_occured),Snackbar.LENGTH_LONG).show()
        }

        return view
    }

    fun listenForData(view: View) {
        val progressSpinner = view.findViewById<ProgressBar>(R.id.progress_spinner)
        progressSpinner.visibility = VISIBLE
        viewModel.pokemon.observe(viewLifecycleOwner) {
            progressSpinner.visibility = GONE
            fillInData(view, it)
        }
        viewModel.error.observe(viewLifecycleOwner){ error ->
            if(error){
                progressSpinner.visibility = GONE
                Snackbar.make(view,getString(R.string.error_has_occured),Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun fillInData(view: View, pokemon: PokemonProfile) {
        val nameTxt = view.findViewById<TextView>(R.id.name_txt)
        val profileImg = view.findViewById<ImageView>(R.id.profile_img)
        val statsGrid = view.findViewById<GridView>(R.id.stats_grid)
        val imagesGrid = view.findViewById<GridView>(R.id.images_grid)
        val textRecycler = view.findViewById<RecyclerView>(R.id.text_list)
        val numberTxt = view.findViewById<TextView>(R.id.number_txt)

        numberTxt.text = getString(R.string.number_pokemon, pokemon.id)
        statsGrid.adapter = StatsGridAdapter(pokemon.stats)
        imagesGrid.adapter = SpritesGridAdapter(pokemon.getSpritesList())
        nameTxt.text = pokemon.name
        Picasso.get().load(pokemon.getImgUrl())
            .placeholder(R.drawable.unknown)
            .priority(Picasso.Priority.HIGH)
            .into(profileImg)
        textRecycler.adapter = TitleTextAdapter(pokemon.getTextInfoList())
        textRecycler.setHasFixedSize(true)
        textRecycler.layoutManager = LinearLayoutManager(requireContext())

        Log.d(TAG, "fillInData: profile: "+pokemon)
    }

    fun createBundle(id: String): Bundle {
        val bundle = Bundle()
        bundle.putString(ID_ARG, id)
        return bundle
    }
}