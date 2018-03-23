package com.recinos.junior.ktpokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

class MainActivity : AppCompatActivity() {

    lateinit var recycler: PokedexAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputS = BufferedInputStream(resources.openRawResource(R.raw.pokemon))
        val isReader = InputStreamReader(inputS)
        val json = JsonReader(isReader)
        val gson = Gson()

        val pokemonEntries = gson.fromJson<Array<PokedexEntry>>(json, Array<PokedexEntry>::class.java)

        val recycler = findViewById<RecyclerView>(R.id.pokedex_recycler)
        recycler.layoutManager = LinearLayoutManager(this)

        recycler.adapter = PokedexAdapter(pokemonEntries)

        val searchBtn = findViewById<Button>(R.id.btn_search)
        searchBtn.setOnClickListener {
            val input = findViewById<EditText>(R.id.input_search)
            val pokedexAdapter = recycler.adapter as PokedexAdapter
            pokedexAdapter.search(input.text.toString())
        }

    }

}
