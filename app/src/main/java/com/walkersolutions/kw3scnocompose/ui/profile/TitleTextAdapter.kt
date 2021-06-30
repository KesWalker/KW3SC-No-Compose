package com.walkersolutions.kw3scnocompose.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.walkersolutions.kw3scnocompose.R
import com.walkersolutions.kw3scnocompose.model.TitleText

class TitleTextAdapter(val texts: List<TitleText>) :
    RecyclerView.Adapter<TitleTextAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titleTxt: TextView
        val dataTxt: TextView

        init{
            titleTxt = view.findViewById(R.id.title_txt)
            dataTxt = view.findViewById(R.id.data_txt)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_text, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val titleAndData = texts[position]
        holder.titleTxt.text = titleAndData.title
        holder.dataTxt.text = titleAndData.text
    }

    override fun getItemCount() = texts.size

}