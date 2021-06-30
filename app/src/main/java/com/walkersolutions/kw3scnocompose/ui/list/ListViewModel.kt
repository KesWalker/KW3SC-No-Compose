package kw.cube.pokemon.ui.list

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kw.cube.pokemon.model.PokemonPreview
import kw.cube.pokemon.repo.PokemonRepo
import kw.cube.pokemon.util.TAG
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repo: PokemonRepo) : ViewModel() {

    private val _pokemons = MutableLiveData<List<PokemonPreview>>()
    val pokemons: LiveData<List<PokemonPreview>> = _pokemons

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

    init {
        searchPokemon()
    }

    /**
     * Retrieves all pokemons with thier names & urls. We know that there are a total of 1118
     * pokemons to get so we get them all at once. A more optimal solution would be to use
     * pagination to retrieve 50 at a time, this would reduce our bandwidth usage.
     */
    fun searchPokemon() {
        viewModelScope.launch {
            try{
                _pokemons.value = repo.search(0, 1118)
            }catch (e: Exception){
                _error.value = true
                Log.d(TAG, "searchPokemon: e: "+e)
            }
        }
    }
}