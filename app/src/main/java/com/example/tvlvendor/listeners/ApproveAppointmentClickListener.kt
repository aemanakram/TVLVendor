package com.example.tvlvendor.listeners

import android.view.View
import com.example.tvlvendor.model.Appointment

interface ApproveAppointmentClickListener {
    fun onItemClick(view: View, appointment: Appointment, position: Int, isApproved: Boolean)
}