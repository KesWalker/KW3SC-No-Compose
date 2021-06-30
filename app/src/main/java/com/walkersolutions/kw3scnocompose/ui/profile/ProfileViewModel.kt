package kw.cube.pokemon.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kw.cube.pokemon.model.PokemonPreview
import kw.cube.pokemon.model.PokemonProfile
import kw.cube.pokemon.repo.PokemonRepo
import kw.cube.pokemon.util.TAG
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repo: PokemonRepo) : ViewModel() {

    private val _pokemon = MutableLiveData<PokemonProfile>()
    val pokemon: LiveData<PokemonProfile> = _pokemon

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

    /**
     * Uses the ID of the pokemon to retrieve more details. If user's device is offline, an exception
     * will be thrown and caught, the 'error' state variable will be changed and the UI will
     * reflect this by showing an error message.
     */
    fun getProfile(id: String) {
        viewModelScope.launch {
            try {
                _pokemon.value = repo.getProfile(id)
            } catch (e: Exception) {
                _error.value = true
                Log.d(TAG, "getProfile: " + e)
            }
        }
    }
}