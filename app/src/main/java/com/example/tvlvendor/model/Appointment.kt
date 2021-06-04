package com.example.tvlvendor.model

import com.google.firebase.Timestamp

data class Appointment(
        val id: String? = null,
        var vendor_id: String? = null,
        var owner_id: String? = null,
        var owner_name: String? = null,
        var time: Timestamp? = null,
        var phone: String? = null,
        var vehicle: UserVehicle? = null
)
