package gst.training.practicefirebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import gst.training.practicefirebase.service.TestFirebaseMessagingService
import gst.training.practicefirebase.ui.UserFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        startService(Intent(this, TestFirebaseMessagingService::class.java))
    }

    private fun setupUI() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, UserFragment.newInstance()).addToBackStack(null).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, TestFirebaseMessagingService::class.java))
    }
}