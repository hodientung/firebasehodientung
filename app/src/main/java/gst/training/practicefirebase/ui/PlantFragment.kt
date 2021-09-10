package gst.training.practicefirebase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import gst.training.practicefirebase.R
import gst.training.practicefirebase.adapter.HomeViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_plant.*


class PlantFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpHome.adapter = HomeViewPagerAdapter(this)
        TabLayoutMediator(
            tlHome, vpHome
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "MY GARDEN"
                else -> tab.text = "PLANT LIST"
            }
        }.attach()
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    companion object {

        @JvmStatic
        fun newInstance() = PlantFragment()
    }
}