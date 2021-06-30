package com.walkersolutions.kw3scnocompose.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.walkersolutions.kw3scnocompose.R
import kw.cube.pokemon.model.PokemonPreview
import kw.cube.pokemon.model.Stat

class StatsGridAdapter(private val stats: List<Stat>) : BaseAdapter() {

    override fun getCount() = stats.size

    override fun getItem(position: Int) = stats[position]

    override fun getItemId(position: Int) = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val stat = stats[position]
        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.list_item_stat, parent, false)!!
        }

        val statNameTxt = view.findViewById<TextView>(R.id.stat_name_txt)
        val statValTxt = view.findViewById<TextView>(R.id.stat_val_txt)

        statNameTxt.text = stat.stat.name
        statValTxt.text = stat.base_stat.toString()

        return view
    }
}