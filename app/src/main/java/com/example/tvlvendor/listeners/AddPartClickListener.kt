package com.example.tvlvendor.listeners

import com.example.tvlvendor.model.Part

interface AddPartClickListener {
    fun onPartClick(part: Part, position:Int)
}