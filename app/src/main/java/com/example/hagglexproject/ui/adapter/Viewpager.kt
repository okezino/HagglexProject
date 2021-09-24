package com.example.hagglexproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hagglexproject.R

class Viewpager : RecyclerView.Adapter<Viewpager.ViewpagerHolder>(){

    var imageList = listOf(R.drawable.viewpagerimage,R.drawable.viewpagerimage,R.drawable.viewpagerimage,R.drawable.viewpagerimage)

    inner class ViewpagerHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val imageItem: ImageView = itemView.findViewById(R.id.item_view_image)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewpagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager,parent,false)

        return ViewpagerHolder(view)
    }

    override fun onBindViewHolder(holder: ViewpagerHolder, position: Int) {

        holder.imageItem.setImageResource(imageList[position])

    }

    override fun getItemCount(): Int {
       return imageList.size
    }


}