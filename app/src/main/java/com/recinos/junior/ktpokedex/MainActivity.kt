package com.recinos.junior.ktpokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.BufferedInputStream
import java.io.InputStreamReader

data class PokedexEntry(
        val id: Int,
        val name: String,
        val types: Array<String>,
        val height: Double,
        val weight: Double,
        val description: String
)

/* This activity is the entry point of the application  */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get array of pokemon Entries
        val pokemonEntries = readPokemonEntries()

        //load recycler view with pokemon entries data
        val recycler = findViewById<RecyclerView>(R.id.pokedex_recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = PokedexAdapter(pokemonEntries)

        val searchBtn = findViewById<Button>(R.id.btn_search)
        //perform search given a user's input when user clicks button
        searchBtn.setOnClickListener {
            val input = findViewById<EditText>(R.id.input_search)
            val pokedexAdapter = recycler.adapter as PokedexAdapter
            pokedexAdapter.search(input.text.toString())
        }

    }

    fun readPokemonEntries(): Array<PokedexEntry> {
        //reading array of json objects from file
        val inputS = BufferedInputStream(resources.openRawResource(R.raw.pokemon))
        val isReader = InputStreamReader(inputS)
        val json = JsonReader(isReader)

        //converting array of json objects into array of PokedexEntry objects
        val gson = Gson()
        return gson.fromJson<Array<PokedexEntry>>(json, Array<PokedexEntry>::class.java)
    }

}
