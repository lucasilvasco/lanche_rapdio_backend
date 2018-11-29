package com.anhanguera.foodshopping.model

class Prato {
    var name: String = ""
    var preco: String = ""
    var icon: Int = 0

    constructor(){}
    constructor(name: String, preco: String, icon: Int){
        this.name = name
        this.preco = preco
        this.icon = icon
    }
}