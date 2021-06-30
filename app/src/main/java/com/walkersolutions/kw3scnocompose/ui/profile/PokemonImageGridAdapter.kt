package com.walkersolutions.kw3scnocompose.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.walkersolutions.kw3scnocompose.R

class SpritesGridAdapter constructor(private val imgUrls: MutableList<String>) : BaseAdapter() {

    override fun getCount() = imgUrls.size

    override fun getItem(position: Int) = imgUrls[position]

    override fun getItemId(position: Int) = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val url = imgUrls[position]
        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.list_item_img, parent, false)!!
        }

        val imgView = view.findViewById<ImageView>(R.id.img_view)

        Picasso.get().load(url)
            .resize(128,128)
            .placeholder(R.drawable.unknown)
            .into(imgView)

        return view
    }

}