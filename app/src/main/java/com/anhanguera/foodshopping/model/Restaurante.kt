package com.anhanguera.foodshopping.model

import android.media.Image

class Restaurante{

    var name: String = ""
    var subtitle: String = ""

    constructor(){}
    constructor(name: String, subtitle: String){
        this.name = name
        this.subtitle = subtitle
    }
}