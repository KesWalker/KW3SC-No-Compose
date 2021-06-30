package com.walkersolutions.kw3scnocompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.walkersolutions.kw3scnocompose.ui.list.PokemonListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_host,PokemonListFragment())
            .commit()
    }
}