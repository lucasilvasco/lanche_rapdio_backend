package com.anhanguera.foodshopping.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
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
            arrayList.add(Restaurante("TESTE", "teste"))

        var restaurantes = findViewById<ListView>(R.id.lvRestaurantes)
        var adapter = RestauranteAdapter(this, arrayList)

        restaurantes.adapter = adapter

    }
}
