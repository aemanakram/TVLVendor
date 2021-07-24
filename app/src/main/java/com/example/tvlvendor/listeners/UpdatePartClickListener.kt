
package com.example.tvlvendor.listeners

import com.example.tvlvendor.model.Part

interface UpdatePartClickListener {
    fun onPartClick(part: Part, position:Int)
}
