package com.example.myzoom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myzoom.databinding.ActivityMainBinding
import com.example.myzoom.fragment.LocationListFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        binding.tvToolbarCustom.text ="臺北市立動物園"

        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.add(R.id.main_content, LocationListFragment(),"LocationListFragment")
        transaction.addToBackStack(null)
        transaction.commitAllowingStateLoss()

    }


    fun setActionBar( title:String,showBack:Boolean,itemClickListener: (Boolean) -> Unit){
        binding.tvToolbarCustom.text =title
        supportActionBar!!.setDisplayHomeAsUpEnabled(showBack);
        supportActionBar!!.setDisplayShowHomeEnabled(showBack);
        if( showBack) {
            binding.toolbar.setNavigationOnClickListener {
                itemClickListener(true)
            }
        }
    }

    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.main_content)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val fragment: Fragment = getCurrentFragment()
        Log.d("ann", "onBackPressed")
    }
}