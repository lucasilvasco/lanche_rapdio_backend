package com.anhanguera.foodshopping.activity

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.anhanguera.foodshopping.R
import com.anhanguera.foodshopping.model.Restaurante
import com.anhanguera.foodshopping.utils.RestauranteAdapter
import android.widget.Toast
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener



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


        var restaurantes = findViewById<GridView>(R.id.gvRestaurantes)

        restaurantes.adapter = RestauranteAdapter(this, arrayList)
        restaurantes.isFocusable = false

        restaurantes.onItemClickListener =   AdapterView.OnItemClickListener { parent, v, position, id ->
            if(position == 0){
                val intent = Intent(this, CardapioActivity::class.java)
                startActivity(intent)

            }
        }

    }
}

