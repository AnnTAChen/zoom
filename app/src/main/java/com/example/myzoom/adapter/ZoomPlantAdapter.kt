package com.example.myzoom.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myzoom.service.ZoomLocationItem
import com.example.myzoom.service.ZoomPlantItem

class ZoomPlantAdapter(val context: Context, private val itemClickListener: (ZoomItem) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var zoomPlantList : List<ZoomPlantItem>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}