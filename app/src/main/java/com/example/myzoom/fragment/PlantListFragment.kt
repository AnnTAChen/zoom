package com.example.myzoom.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myzoom.R
import com.example.myzoom.adapter.ZoomAdapter
import com.example.myzoom.databinding.PlantListFragmentBinding
import com.example.myzoom.service.Restful
import com.example.myzoom.service.ZoomDataSource
import com.example.myzoom.service.ZoomLocationItem
import com.example.myzoom.service.ZoomPlantItem
import com.example.myzoom.vm.ZoomRepository
import com.example.myzoom.vm.ZoomViewModal

class PlantListFragment(val currentLocation:String) :Fragment() {
    private lateinit var layoutBinding :PlantListFragmentBinding
    private lateinit var zoomPlantAdapter: ZoomAdapter
    private val zoomViewModal: ZoomViewModal by lazy {
        ViewModelProvider(
            requireActivity(),
            ZoomViewModal.Factory(ZoomRepository(ZoomDataSource(Restful.createZoomAPIService())))
        ).get(
            ZoomViewModal::class.java
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutBinding = DataBindingUtil.inflate(inflater, R.layout.plant_list_fragment, container,
            false)
        return layoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zoomPlantAdapter = context?.let {
            ZoomAdapter(it,ZoomAdapter.PLANT) {
                Log.d("ann","click:" + it.toString())
                val manager: FragmentManager = parentFragmentManager
                val transaction: FragmentTransaction = manager.beginTransaction()
                transaction.setCustomAnimations(
                    R.anim.right_in,
                    R.anim.right_out,
                    R.anim.right_in,
                    R.anim.right_out).replace(R.id.main_content, PlantDetailFragment(it as ZoomPlantItem),"PlantDetailFragment")
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commitAllowingStateLoss()
            }
        }!!
        layoutBinding.rcPlantList.layoutManager = LinearLayoutManager(requireContext())
        layoutBinding.rcPlantList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        layoutBinding.rcPlantList.adapter = zoomPlantAdapter
        observe()
        zoomViewModal.getPlantList()
    }

    fun observe(){
        zoomViewModal.zoomPlanList.observe(viewLifecycleOwner){
            val result = it.filter { it.F_Location.split("；").contains(currentLocation) }.toList()
            zoomPlantAdapter.setPlantData(result)
        }
    }
}