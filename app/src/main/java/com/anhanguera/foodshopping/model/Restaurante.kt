package com.anhanguera.foodshopping.model

import android.graphics.drawable.Drawable
import com.anhanguera.foodshopping.R


class Restaurante{

    var name: String = ""
    var backgroundImage: Int = R.drawable.logo_circle
    var cardapio: Cardapio = Cardapio()

    constructor(){}
    constructor(name: String, backgroundImage: Int){
        this.name = name
        this.backgroundImage = backgroundImage
    }
}