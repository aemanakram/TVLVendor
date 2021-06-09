package com.example.tvlvendor.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvlvendor.R
import com.example.tvlvendor.adapter.PartsAdapter
import com.example.tvlvendor.listeners.PartClickListener
import com.example.tvlvendor.model.Part
import com.example.tvlvendor.viewmodel.ViewPartsViewModel

class ViewPartsActivity : AppCompatActivity(), PartClickListener {

    private var partsViewModel: ViewPartsViewModel = ViewPartsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_parts)

        val partsRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_parts)
        partsRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        val partsAdapter = PartsAdapter(emptyList(), this)

        partsRecyclerView.adapter = partsAdapter

        partsViewModel.data.observe(this, Observer {
            Log.d("PART", "UPDATING")
            partsAdapter.dataSet = it
            partsAdapter.notifyDataSetChanged()
        })


        partsViewModel.loadParts()
    }


}