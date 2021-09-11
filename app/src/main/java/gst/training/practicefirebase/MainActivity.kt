package gst.training.practicefirebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import gst.training.practicefirebase.ui.UserFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
    }

    private fun setupUI() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, UserFragment.newInstance()).addToBackStack(null).commit()
    }
}