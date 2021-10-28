package com.geektech.kotlin1hw3.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geektech.kotlin1hw3.R
import com.geektech.kotlin1hw3.databinding.ActivityMainBinding
import com.geektech.kotlin1hw3.ui.adapter.Adapter

class MainActivity : AppCompatActivity(), Adapter.Listener {

    private lateinit var ui: ActivityMainBinding
    private lateinit var adapter: Adapter
    private var listOfImage = arrayListOf<String>()
    private var sendImages = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        setupList()
        initViews()
        setupListener()
    }

    private fun setupList() {
        for (i in 1..3)
            listOfImage.addAll(resources.getStringArray(R.array.image_urls))
    }

    private fun initViews() {
        adapter = Adapter(listOfImage, this)
        ui.rvMainAdapter.adapter = adapter
    }

    override fun onLongClick(id: Int) {
        sendImages += listOfImage[id] + " "
    }

    private fun setupListener() {
        ui.btnSend.setOnClickListener {
            val intent = Intent(this, GalleryDetailActivity::class.java)
            intent.putExtra(image_key, sendImages)
            startActivity(intent)
        }
    }

    companion object {
        const val image_key = "SOME_IMAGE_KEY"
    }
}