package com.example.myzoom.fragment

import android.content.Intent
import android.net.Uri
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
import coil.load
import com.example.myzoom.MainActivity
import com.example.myzoom.R
import com.example.myzoom.databinding.LocationDetailFragmentBinding
import com.example.myzoom.service.Restful
import com.example.myzoom.service.ZoomDataSource
import com.example.myzoom.service.ZoomLocationItem
import com.example.myzoom.vm.ZoomRepository
import com.example.myzoom.vm.ZoomViewModal


class LocationDetailFragment(val focusLocation:Int) :Fragment(){
    private lateinit var layourtBinding:LocationDetailFragmentBinding
    private lateinit var currentLocation : ZoomLocationItem
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
        layourtBinding = DataBindingUtil.inflate(inflater, R.layout.location_detail_fragment, container,
            false)
        return layourtBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentLocation = zoomViewModal.zoomLocationList.value?.find { it.id == focusLocation }!!
        (activity as MainActivity).setActionBar(currentLocation.name,true)
        layourtBinding.imgLocation.load(currentLocation.pic_url)
        layourtBinding.tvLocationInfo.text = currentLocation.info
        layourtBinding.tvLocationMemo.text = currentLocation.memo
        layourtBinding.tvLocationCategory.text  = currentLocation.category
        layourtBinding.tvLocationMore.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(currentLocation.url))
            startActivity(browserIntent)
        }

    }
    override fun onResume() {
        super.onResume()
        Log.d("ann","onResume")
        val manager: FragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.right_in,
            R.anim.right_out,
            R.anim.right_in,
            R.anim.right_out).add(R.id.plan_list_container, PlantListFragment(currentLocation.name),"PlantListFragment")
        //transaction.addToBackStack(null)
        transaction.commitAllowingStateLoss()
    }

    fun onBackPress(){
        Log.d("ann", "onBackPressed:" +requireActivity().supportFragmentManager.backStackEntryCount)

    }
}