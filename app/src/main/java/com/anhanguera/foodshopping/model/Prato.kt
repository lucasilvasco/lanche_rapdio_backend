package com.anhanguera.foodshopping.model

class Prato {
    var name: String = ""
    var preco: Float = 0.0f
    var icon: Int = 0

    constructor(){}
    constructor(name: String, preco: Float, icon: Int){
        this.name = name
        this.preco = preco
        this.icon = icon
    }
}