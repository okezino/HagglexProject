package com.example.hagglexproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hagglexproject.R
import com.example.hagglexproject.model.RecyclerviewItems

class MainRecyclerView  : RecyclerView.Adapter<MainRecyclerView.ViewHolder>() {

    var list = listOf(
        RecyclerviewItems(R.drawable.hagglogo,R.drawable.hagg,"Haggle  (HAG)","NGN 380"),
        RecyclerviewItems(R.drawable.tetherlogo,R.drawable.tether,"Tether  (USDT)","NGN 4272.14"),
        RecyclerviewItems(R.drawable.hagglogo,R.drawable.hagg,"Haggle  (HAG)","NGN 380"),
        RecyclerviewItems(R.drawable.tetherlogo,R.drawable.tether,"Tether  (USDT)","NGN 4272.14"),
        RecyclerviewItems(R.drawable.hagglogo,R.drawable.hagg,"Haggle  (HAG)","NGN 380"),
        RecyclerviewItems(R.drawable.tetherlogo,R.drawable.tether,"Tether  (USDT)","NGN 4272.14"),
        RecyclerviewItems(R.drawable.hagglogo,R.drawable.hagg,"Haggle  (HAG)","NGN 380"),
        RecyclerviewItems(R.drawable.tetherlogo,R.drawable.tether,"Tether  (USDT)","NGN 4272.14"),
        RecyclerviewItems(R.drawable.hagglogo,R.drawable.hagg,"Haggle  (HAG)","NGN 380"),
        RecyclerviewItems(R.drawable.tetherlogo,R.drawable.tether,"Tether  (USDT)","NGN 4272.14"),
        RecyclerviewItems(R.drawable.hagglogo,R.drawable.hagg,"Haggle  (HAG)","NGN 380"),
        RecyclerviewItems(R.drawable.tetherlogo,R.drawable.tether,"Tether  (USDT)","NGN 4272.14"),
        RecyclerviewItems(R.drawable.hagglogo,R.drawable.hagg,"Haggle  (HAG)","NGN 380"))


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val logo : ImageView = itemView.findViewById(R.id.item_logo)
        val graph : ImageView = itemView.findViewById(R.id.item_graph)
        val title : TextView = itemView.findViewById(R.id.item_title)
        val textValue : TextView = itemView.findViewById(R.id.item_value_re)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = list[position]
      holder.logo.setImageResource(items.logo)
        holder.graph.setImageResource(items.graph)
        holder.textValue.text = items.value
        holder.title.text = items.title
    }

    override fun getItemCount(): Int {

        return list.size

    }
}