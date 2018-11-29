package com.anhanguera.foodshopping.utils

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.anhanguera.foodshopping.R
import com.anhanguera.foodshopping.model.Prato
import java.util.ArrayList

class CardapioAdapter(private var activity: Activity, private var items: ArrayList<Prato>) : BaseAdapter() {
    private class ViewHolder(row: View?) {

        var pratoImage: ImageView? = null
        var pratoName: TextView? = null
        var pratoPreco: TextView? = null

        init {
            this.pratoImage = row?.findViewById(R.id.pratoImage)
            this.pratoName = row?.findViewById(R.id.pratoName)
            this.pratoPreco = row?.findViewById(R.id.pratoPreco)

        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder

        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_list_cardapio, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var restauranteDto = items[position]
        viewHolder.pratoImage?.setImageResource(restauranteDto.icon)
        viewHolder.pratoName?.text = restauranteDto.name
        viewHolder.pratoPreco?.text = restauranteDto.preco


        return view as View
    }

    override fun getItem(i: Int): Any {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}