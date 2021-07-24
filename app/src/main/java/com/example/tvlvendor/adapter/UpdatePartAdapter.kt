//UpdatePartAdapter

package com.example.tvlvendor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvlvendor.R
import com.example.tvlvendor.listeners.PartClickListener
import com.example.tvlvendor.listeners.UpdatePartClickListener
import com.example.tvlvendor.model.Part

class UpdatePartAdapter (var dataSet: List<Part>,
                         private val itemClickListener: UpdatePartClickListener
) :
    RecyclerView.Adapter<UpdatePartAdapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.label_name)
        val type: TextView = view.findViewById(R.id.label_type)
        val life: TextView = view.findViewById(R.id.label_life)
        val description: TextView = view.findViewById(R.id.label_description)
        val price: TextView = view.findViewById(R.id.label_price)
        val quantity: TextView = view.findViewById(R.id.label_quantity)

        init {
            // Define click listener for the ViewHolder's View.
        }
        fun bind(part: Part, position: Int, clickListener: UpdatePartClickListener)
        {
            view.setOnClickListener {
                clickListener.onPartClick(part,position)
//                clickListener.onItemClick(part, position)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.part_item_detailed, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val part = dataSet[position]
        viewHolder.name.text = part.name
        viewHolder.type.text = part.type
        viewHolder.life.text = part.life
        viewHolder.description.text = part.description
        val priceText = "Rs. ${part.price}"
        val quantityText = "x ${part.quantity}"
        viewHolder.price.text = priceText
        viewHolder.quantity.text = quantityText
        viewHolder.bind(part, position, itemClickListener)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

/**
 * Provide a reference to the type of views that you are using
 * (custom ViewHolder).
 */