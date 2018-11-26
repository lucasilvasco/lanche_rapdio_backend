package com.anhanguera.foodshopping.activity

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.anhanguera.foodshopping.R
import com.anhanguera.foodshopping.model.Restaurante
import com.anhanguera.foodshopping.utils.RestauranteAdapter

class ShoppingHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_home)

        var shoppingName = findViewById<TextView>(R.id.tvShoppingName)
        shoppingName.text = intent.extras["shopping"].toString()

        var arrayList = ArrayList<Restaurante>()
        arrayList.add(Restaurante("Giraffas", R.drawable.logo_giraffas))
        arrayList.add(Restaurante("Mc Donalds", R.drawable.logo_mcdonalds))
        arrayList.add(Restaurante("Burguer King", R.drawable.logo_bk))
        arrayList.add(Restaurante("Giraffas", R.drawable.logo_giraffas))
        arrayList.add(Restaurante("Mc Donalds", R.drawable.logo_mcdonalds))
        arrayList.add(Restaurante("Burguer King", R.drawable.logo_bk))

        var restaurantes = findViewById<GridView>(R.id.gvRestaurantes)
        var adapter =  RestauranteAdapter(this, arrayList)

        restaurantes.adapter = adapter

        restaurantes.setOnItemClickListener { AdapterView, view, i, l ->
            Toast.makeText(this, "teste", Toast.LENGTH_LONG).show()
        }
    }
}

