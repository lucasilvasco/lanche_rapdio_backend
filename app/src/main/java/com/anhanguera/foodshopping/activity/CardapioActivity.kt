package com.anhanguera.foodshopping.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.anhanguera.foodshopping.R
import com.anhanguera.foodshopping.model.Prato
import com.anhanguera.foodshopping.model.Restaurante
import com.anhanguera.foodshopping.utils.CardapioAdapter
import com.anhanguera.foodshopping.utils.RestauranteAdapter

class CardapioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardapio)

        var arrayList = ArrayList<Prato>()
        arrayList.add(Prato("Brutus", "R$16,90", R.drawable.brutus_image))

        var cardapio = findViewById<ListView>(R.id.lista_cardapio)

        cardapio.adapter = CardapioAdapter(this, arrayList)


    }
}
