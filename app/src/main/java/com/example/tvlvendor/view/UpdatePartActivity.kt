package com.example.tvlvendor.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tvlvendor.R
import com.example.tvlvendor.adapter.UpdatePartAdapter
import com.example.tvlvendor.listeners.UpdatePartClickListener
import com.example.tvlvendor.viewmodel.UpdatePartViewModel

import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvlvendor.model.Part


class UpdatePartActivity : AppCompatActivity(), UpdatePartClickListener {
    private var partsViewModel: UpdatePartViewModel = UpdatePartViewModel()
    private lateinit var priceText: String
    private lateinit var quantityText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_part)
        val partsRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_View_parts)
        partsRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        val updatePartsAdapter = UpdatePartAdapter(emptyList(), this)

        partsRecyclerView.adapter = updatePartsAdapter

        partsViewModel.data.observe(this, Observer {
            Log.d("PART", "UPDATING")
            updatePartsAdapter.dataSet = it
            updatePartsAdapter.notifyDataSetChanged()
        })


        partsViewModel.loadParts()

    }


    override fun onPartClick(part: Part, position: Int) {

        Log.d("Add Part","Enter Info")
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Update Part in Inventory")
        alert.setMessage("Enter quantity and price of Item.")

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val price = EditText(this)
        price.setSingleLine()
        price.hint = "Enter updated price of each part"
        layout.addView(price)

        val quantity = EditText(this)
        quantity.setSingleLine()
        quantity.hint = "Enter updated quantity of items"
        layout.addView(quantity)

        layout.setPadding(50, 40, 50, 10)

        alert.setView(layout)

        alert.setPositiveButton("Proceed") { _, _ ->
            priceText = price.text.toString()
            quantityText = quantity.text.toString()

            Log.i("xxx",priceText )
            Log.i("xxx",quantityText )

            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_LONG).show()

            update(part,priceText.toInt(),quantityText.toInt())
        }

        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alert.setCancelable(false)
        alert.show()

    }
    private fun update(part:Part, price :Int, quantity :Int){
        partsViewModel.updatePart(part,price,quantity)
        val value  = Intent(this, MainActivity::class.java)
        startActivity(value)
       // partsViewModel.addPart(part,price,quantity)
    }


}
