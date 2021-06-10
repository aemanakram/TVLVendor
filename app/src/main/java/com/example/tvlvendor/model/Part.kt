package com.example.tvlvendor.model

data class Part (
        val id: String?,
        var name: String? = null,
        var description: String? = null,
        var life: String? = null,
        var type: String? = null,
        var price: Number? = null,
        var quantity: Number? = null
)