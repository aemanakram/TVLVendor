package com.example.tvlvendor.model


import com.google.android.gms.maps.model.LatLng

data class Vendor (
        val id: String?,
        var address: String?,
        var location: LatLng?
)