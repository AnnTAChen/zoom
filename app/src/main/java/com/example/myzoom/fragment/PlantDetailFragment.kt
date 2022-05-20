package com.example.myzoom.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import coil.load
import com.example.myzoom.MainActivity
import com.example.myzoom.R
import com.example.myzoom.databinding.PlantDetailFragmentBinding
import com.example.myzoom.service.ZoomPlantItem

class PlantDetailFragment(val currentPlant: ZoomPlantItem): Fragment() {
    private lateinit var layoutBinding : PlantDetailFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutBinding = DataBindingUtil.inflate(inflater, R.layout.plant_detail_fragment, container,
            false)
        return layoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutBinding.imgLocation.load(currentPlant.F_Pic01_URL)
        layoutBinding.tvPlantChname.text = currentPlant.F_Name_Ch
        layoutBinding.tvPlantEnname.text = currentPlant.F_Name_En
        layoutBinding.tvPlantAlsoknown.text = currentPlant.F_AlsoKnown
        layoutBinding.tvPlantBrief.text = currentPlant.F_Brief
        layoutBinding.tvPlantFeature.text = currentPlant.F_Feature
        layoutBinding.tvPlantFunction.text = currentPlant.F_FunctionApplication
        layoutBinding.tvPlantUpdate.text = currentPlant.F_Update
        (activity as MainActivity).setActionBar(currentPlant.F_Name_Ch,true)
    }
}