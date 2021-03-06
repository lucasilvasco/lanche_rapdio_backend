package com.anhanguera.foodshopping.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.anhanguera.foodshopping.R

class SelectShopping : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_shopping)

    var shoppingGroup = findViewById<RadioGroup>(R.id.shoppingGroup)
    var nextButton = findViewById<Button>(R.id.nextButtonSelectShopping)

        shoppingGroup.check(0)

        nextButton.setOnClickListener {
            var selectedId = shoppingGroup.checkedRadioButtonId
            var radioButtonSelected = findViewById<RadioButton>(selectedId)

            val intent = Intent(this, ShoppingHome::class.java)
            intent.putExtra("shopping", radioButtonSelected.text)
            startActivity(intent)
        }




    }

}