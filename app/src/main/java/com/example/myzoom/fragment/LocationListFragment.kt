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
import com.example.myzoom.MainActivity
import com.example.myzoom.R
import com.example.myzoom.adapter.ZoomAdapter
import com.example.myzoom.databinding.LocationListFragmentBinding
import com.example.myzoom.service.Restful
import com.example.myzoom.service.ZoomDataSource
import com.example.myzoom.service.ZoomLocationItem
import com.example.myzoom.vm.ZoomRepository
import com.example.myzoom.vm.ZoomViewModal


class LocationListFragment : Fragment() {
    private lateinit var layourtBinding: LocationListFragmentBinding
    private lateinit var zoomLocationAdapter: ZoomAdapter
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
        layourtBinding = DataBindingUtil.inflate(inflater, R.layout.location_list_fragment, container,
            false)
        return layourtBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setActionBar("臺北市立動物園",false)
        zoomLocationAdapter = context?.let {
            ZoomAdapter(it,ZoomAdapter.LOCATION) {
                Log.d("ann","click:" + it.toString())
                val manager: FragmentManager = parentFragmentManager
                val transaction: FragmentTransaction = manager.beginTransaction()
                transaction.setCustomAnimations(
                    R.anim.right_in,
                    R.anim.right_out,
                    R.anim.right_in,
                    R.anim.right_out).replace(R.id.main_content, LocationDetailFragment((it as ZoomLocationItem).id),"LocationDetailFragment")
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commitAllowingStateLoss()
            }
        }!!
        layourtBinding.rcLocationList.layoutManager = LinearLayoutManager(requireContext())
        layourtBinding.rcLocationList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        layourtBinding.rcLocationList.adapter = zoomLocationAdapter
        observe()
        zoomViewModal.getZoomLocation()

    }
    override fun onResume() {
        super.onResume()
    }

    private fun observe(){
        zoomViewModal.zoomLocationList.observe(viewLifecycleOwner) {
            Log.d("ann", "zoomLocationList:" + it.size)
            zoomLocationAdapter.setLocationData(it)
        }
    }




}