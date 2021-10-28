package com.geektech.kotlin1hw3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geektech.kotlin1hw3.databinding.ActivityGalleryDetailBinding
import com.geektech.kotlin1hw3.ui.adapter.Adapter

class GalleryDetailActivity : AppCompatActivity() {

    private lateinit var ui: ActivityGalleryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = ActivityGalleryDetailBinding.inflate(layoutInflater)
        setContentView(ui.root)

        checkIntent()
    }

    private fun checkIntent() {
        val urls = intent.getStringExtra(MainActivity.image_key)
        if (!urls.isNullOrEmpty())
            initViews(urls)
    }

    private fun initViews(urls: String) {
        val adapter = Adapter(stringToArray(urls), object : Adapter.Listener {
            override fun onLongClick(id: Int) {}
        })
        ui.rvDetail.adapter = adapter

    }

    private fun stringToArray(s: String): ArrayList<String> {
        val list = s.trim().splitToSequence(' ')
            .filter { it.isNotEmpty() } // or: .filter { it.isNotBlank() }
            .toMutableList()

        return list as ArrayList<String>
    }
}