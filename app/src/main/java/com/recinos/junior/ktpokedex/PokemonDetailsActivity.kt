package com.recinos.junior.ktpokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class PokemonDetailsActivity : AppCompatActivity() {

    val POKEMON_NAME = "com.recinos.POKEMON_NAME"
    val POKEMON_TYPES = "com.recinos.POKEMON_TYPES"
    val POKEMON_DESCRIPTION = "com.recinos.POKEMON_DESCRIPTION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details_activty)

        val pName = intent.getStringExtra(POKEMON_NAME)
        val pTypes = intent.getStringArrayExtra(POKEMON_TYPES)
        val pDescription = intent.getStringExtra(POKEMON_DESCRIPTION)

        val textName = findViewById<TextView>(R.id.text_pokemon_name)
        val textType1 = findViewById<TextView>(R.id.text_type1)
        val textType2 = findViewById<TextView>(R.id.text_type2)
        val textDescription = findViewById<TextView>(R.id.text_description_content)
        val image = findViewById<ImageView>(R.id.image_pokemon_detail)

        image.setImageResource(R.drawable.pokeball2)
        textName.text = pName
        textDescription.text = pDescription


        when(pTypes.size) {
            1 -> {
                textType1.text = pTypes[0]
                textType2.alpha = 0.0f
            }

            2 -> {
                textType1.text = pTypes[0]
                textType2.text = pTypes[1]
                textType2.alpha = 1.0f
            }
            else -> {
                textType1.alpha = 0.0f
                textType2.alpha = 0.0f
            }

        }

    }
}
