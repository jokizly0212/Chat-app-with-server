package com.example.dell.dmchat

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class SectionsPageAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    private val fragmentList = ArrayList<Fragment>()

    fun addFragment(fragment : Fragment) {
        fragmentList.add(fragment)
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}
