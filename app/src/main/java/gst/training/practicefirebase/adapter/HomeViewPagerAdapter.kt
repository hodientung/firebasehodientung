package gst.training.practicefirebase.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import gst.training.practicefirebase.ui.MyGardenFragment
import gst.training.practicefirebase.ui.PlantListFragment

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MyGardenFragment.newInstance()
        else -> PlantListFragment.newInstance()
    }

}