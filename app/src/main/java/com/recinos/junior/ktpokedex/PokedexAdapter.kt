package com.recinos.junior.ktpokedex

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Junior on 3/22/2018.
 */
class PokedexAdapter(pk: Array<PokedexEntry>): RecyclerView.Adapter<PokemonViewHolder>() {

    val POKEMON_NAME = "com.recinos.POKEMON_NAME"
    val POKEMON_TYPES = "com.recinos.POKEMON_TYPES"
    val POKEMON_DESCRIPTION = "com.recinos.POKEMON_DESCRIPTION"
    private var pokemonEntries: Array<PokedexEntry> = pk
    private var pokemonSearchResults: List<PokedexEntry> = listOf()



    override fun getItemCount(): Int {
            return if (pokemonSearchResults.isEmpty()) pokemonEntries.size else pokemonSearchResults.size
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val pokemonEntry = layoutInflater.inflate(R.layout.pokemon_overview_view, parent, false)
        return PokemonViewHolder(pokemonEntry)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder?, position: Int) {
        val pokemonView = holder?.view
        val pokemon =
                if (pokemonSearchResults.isEmpty())
                    pokemonEntries[position]
                else
                     pokemonSearchResults[position]

        pokemonView?.setOnClickListener {
            val intent = Intent(pokemonView.context, PokemonDetailsActivity::class.java).apply {
                putExtra(POKEMON_NAME, pokemon.name)
                putExtra(POKEMON_TYPES, pokemon.types)
                putExtra(POKEMON_DESCRIPTION, pokemon.description)
            }
            pokemonView.context.startActivity(intent)
        }

        pokemonView?.findViewById<TextView>(R.id.text_pokemon_name)?.text = pokemon.name
        val img = pokemonView?.findViewById<ImageView>(R.id.image_pokemon)
        img?.setImageResource(R.drawable.pokeball2)

    }

    fun search(input: String) {
        pokemonSearchResults = pokemonEntries.filter {
            it.name.contains(input.toLowerCase())
        }
        notifyDataSetChanged()
    }

}

class PokemonViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}