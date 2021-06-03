package com.example.tvlvendor.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tvlvendor.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPartsButton = findViewById<View>(R.id.ViewPartsButton)
        val updateLocationButton = findViewById<View>(R.id.UpdateLocationButton)

        viewPartsButton.setOnClickListener {
            val value  = Intent(this, ViewPartsActivity::class.java)
            startActivity(value)
        }
        updateLocationButton.setOnClickListener {
            val value  = Intent(this, UpdateMapsActivity::class.java)
            startActivity(value)
        }
    }
}