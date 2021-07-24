package com.example.tvlvendor.view

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvlvendor.R
import com.example.tvlvendor.adapter.AddPartAdapter
import com.example.tvlvendor.listeners.AddPartClickListener
import com.example.tvlvendor.model.Part
import com.example.tvlvendor.viewmodel.AddPartViewModel


class AddPartActivity : AppCompatActivity(), AddPartClickListener {
    private var partsViewModel: AddPartViewModel = AddPartViewModel()
    private lateinit var priceText: String
    private lateinit var quantityText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_part)
        val partsRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_parts)
        partsRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        val addPartsAdapter = AddPartAdapter(emptyList(), this)

        partsRecyclerView.adapter = addPartsAdapter

        partsViewModel.data.observe(this, Observer {
            Log.d("PART", "UPDATING")
            addPartsAdapter.dataSet = it
            addPartsAdapter.notifyDataSetChanged()
        })


        partsViewModel.loadParts()

    }



    override fun onPartClick(part: Part, position: Int) {
        Log.d("Add Part","Enter Info")
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Add Part in Inventory")
        alert.setMessage("Enter quantity and price of Item.")

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val price = EditText(this)
        price.setSingleLine()
        price.hint = "Enter price of each part"
        layout.addView(price)

        val quantity = EditText(this)
        quantity.setSingleLine()
        quantity.hint = "Enter quantity of items"
        layout.addView(quantity)

        layout.setPadding(50, 40, 50, 10)

        alert.setView(layout)

        alert.setPositiveButton("Proceed") { _, _ ->
            priceText = price.text.toString()
            quantityText = quantity.text.toString()

            Log.i("xxx",priceText )
            Log.i("xxx",quantityText )

            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_LONG).show()

            add(part,priceText.toInt(),quantityText.toInt())
        }

        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alert.setCancelable(false)
        alert.show()

    }
    fun add(part:Part,price :Int,quantity :Int){
        partsViewModel.addPart(part,price,quantity)
    }


}
