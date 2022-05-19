package com.example.myzoom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.myzoom.databinding.ZoomLoactionItemBinding
import com.example.myzoom.service.ZoomLocationItem
import com.example.myzoom.service.ZoomPlantItem

class ZoomAdapter (val context: Context, private var type : Int,
                   private val itemClickListener: (ZoomItem) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var zoomLocationList : List<ZoomLocationItem>? = null
    private var zoomPlantList : List<ZoomPlantItem>? = null
    companion object{
        val LOCATION = 1
        val PLANT = 2
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if( type == LOCATION ){
            return ZoomLocationAdapterVH.from(parent)
        }else if( type == PLANT ){
            return ZoomPlantAdapterVH.from(parent)
        }
        return ZoomLocationAdapterVH.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if( type == LOCATION ){
            val listViewHolder = holder as ZoomLocationAdapterVH
            listViewHolder.bind(
                zoomLocationList?.get(position)!!, itemClickListener
            )
        }else if( type == PLANT ){
            val listViewHolder = holder as ZoomPlantAdapterVH
            listViewHolder.bind(
                zoomPlantList?.get(position)!!, itemClickListener
            )
        }

    }

    override fun getItemCount(): Int {
        if( type == LOCATION ){
            if(  zoomLocationList == null )
                return 0
            return zoomLocationList?.size!!
        }else{
            if(  zoomPlantList == null )
                return 0
            return zoomPlantList?.size!!
        }

    }

    fun setLocationData(datas : List<ZoomLocationItem>){
        zoomLocationList = datas;
        notifyDataSetChanged()
    }

    fun setPlantData(datas : List<ZoomPlantItem>){
        zoomPlantList = datas;
        notifyDataSetChanged()
    }

    class ZoomLocationAdapterVH : RecyclerView.ViewHolder {
        private var context: Context
        private lateinit  var zoomLoactionItemBinding : ZoomLoactionItemBinding
        constructor(context: Context, binding : ZoomLoactionItemBinding) : super(binding.root) {
            this.context = context
            zoomLoactionItemBinding = binding
        }
        fun bind(zoomItem: ZoomLocationItem, itemClickListener: (ZoomLocationItem) -> Unit){
            with(zoomLoactionItemBinding) {
                tvLocationName.text = (zoomItem.name)
                tvLocationInfo.text = zoomItem.info
                tvLocationMemo.text = zoomItem.memo
                imgLocation.load(zoomItem.pic_url){
                    crossfade(500)
                    scale(Scale.FILL)
                }
                wholeItem.setOnClickListener {
                    itemClickListener(zoomItem)
                }
            }
        }
        companion object{
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                var binding : ViewDataBinding
                binding =
                    ZoomLoactionItemBinding.inflate(layoutInflater, parent, false)
                return return ZoomLocationAdapterVH(parent.context, binding)
                return ZoomLocationAdapterVH(parent.context, binding)
            }
        }
    }

    class ZoomPlantAdapterVH : RecyclerView.ViewHolder {
        private var context: Context
        private lateinit  var zoomLoactionItemBinding : ZoomLoactionItemBinding
        constructor(context: Context, binding : ZoomLoactionItemBinding) : super(binding.root) {
            this.context = context
            zoomLoactionItemBinding = binding
        }
        fun bind(zoomItem: ZoomPlantItem, itemClickListener: (ZoomPlantItem) -> Unit){
            with(zoomLoactionItemBinding) {
                tvLocationName.text = (zoomItem.F_Name_Ch)
                tvLocationInfo.text = zoomItem.F_AlsoKnown
                imgLocation.load(zoomItem.F_Pic01_URL){
                    crossfade(500)
                    scale(Scale.FILL)
                }
                wholeItem.setOnClickListener {
                    itemClickListener(zoomItem)
                }
            }
        }
        companion object{
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                var binding : ViewDataBinding
                binding =
                    ZoomLoactionItemBinding.inflate(layoutInflater, parent, false)
                return return ZoomPlantAdapterVH(parent.context, binding)
                return ZoomPlantAdapterVH(parent.context, binding)
            }
        }
    }

}