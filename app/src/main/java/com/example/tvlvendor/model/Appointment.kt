package com.example.tvlvendor.model

import com.google.firebase.Timestamp

data class Appointment(
        val id: String?,
        var vendor_id: String?,
        var owner_id: String?,
        var owner_name: String?,
        var time: Timestamp?,
        var phone: String?
)
