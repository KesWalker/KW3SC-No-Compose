package com.walkersolutions.kw3scnocompose.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kw.cube.pokemon.network.PokemonService
import kw.cube.pokemon.repo.PokemonRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Used by Hilt to inject our PokemonRepo into the view models that need it
     * https://developer.android.com/training/dependency-injection/hilt-android
     */
    @Provides
    @Singleton
    fun providePokemonRepo(pokemonService: PokemonService): PokemonRepo =
        PokemonRepo(pokemonService)


    /**
     * Injects our service instance into the repo class.
     */
    @Provides
    @Singleton
    fun providePokemonService(): PokemonService =
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(PokemonService::class.java)
}