package com.recinos.junior.ktpokedex

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/* *
    The PokedexAdapter class binds the pokemon entries data to the list of views of the recycler view
 */
class PokedexAdapter(pk: Array<PokedexEntry>): RecyclerView.Adapter<PokemonViewHolder>() {

    private val POKEMON_NAME = "com.recinos.POKEMON_NAME"
    private val POKEMON_TYPES = "com.recinos.POKEMON_TYPES"
    private val POKEMON_DESCRIPTION = "com.recinos.POKEMON_DESCRIPTION"

    //entire list of pokemon entries
    private var pokemonEntries: Array<PokedexEntry> = pk
    //list of pokemon entries for a given search
    private var pokemonSearchResults: List<PokedexEntry> = listOf()

    //returns number of pokemon entries to display
    override fun getItemCount(): Int {
            return if (pokemonSearchResults.isEmpty()) pokemonEntries.size else pokemonSearchResults.size
    }

    //creates the view for each item in the recycler view
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val pokemonEntry = layoutInflater.inflate(R.layout.pokemon_overview_view, parent, false)
        return PokemonViewHolder(pokemonEntry)
    }

    //bind data from pokemon entries to views to display
    override fun onBindViewHolder(holder: PokemonViewHolder?, position: Int) {
        val pokemonView = holder?.view
        val pokemon =
                if (pokemonSearchResults.isEmpty())
                    pokemonEntries[position]
                else
                     pokemonSearchResults[position]

        pokemonView?.findViewById<TextView>(R.id.text_pokemon_name)?.text = pokemon.name
        val img = pokemonView?.findViewById<ImageView>(R.id.image_pokemon)
        img?.setImageResource(R.drawable.pokeball2)

        pokemonView?.setOnClickListener {
            val intent = Intent(pokemonView.context, PokemonDetailsActivity::class.java).apply {
                putExtra(POKEMON_NAME, pokemon.name)
                putExtra(POKEMON_TYPES, pokemon.types)
                putExtra(POKEMON_DESCRIPTION, pokemon.description)
            }
            pokemonView.context.startActivity(intent)
        }
    }

    //search pokemon entries by matching the input parameter with the pokemon's name
    fun search(input: String) {
        pokemonSearchResults = pokemonEntries.filter {
            it.name.contains(input.toLowerCase())
        }
        notifyDataSetChanged()
    }
}

//require class for recycler view to work properly
class PokemonViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}