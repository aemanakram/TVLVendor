package com.example.tvlvendor.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tvlvendor.model.Appointment
import com.example.tvlvendor.model.Part
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ViewAppointmentsViewModel {
    private lateinit var auth: FirebaseAuth
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var data = MutableLiveData<List<Appointment>>()

    fun loadAppointments(){
        auth = FirebaseAuth.getInstance()
        db.collection("Appointment").whereEqualTo("vendor_id", auth.currentUser!!.uid).get().addOnSuccessListener { res ->

            val appointments = mutableListOf<Appointment>()
            val owner_ids = mutableListOf<String>()
            for (doc in res){
                owner_ids.add(doc["owner_id"] as String)
                appointments.add(Appointment(id=doc.id, time=doc["time"] as Timestamp?, owner_id = doc["owner_id"] as String?,
                vendor_id = auth.currentUser!!.uid, phone=null, owner_name=null))
            }
            if(owner_ids.size != 0) {
                db.collection("Owner").whereIn("uid", owner_ids).get().addOnSuccessListener { querySnapshot ->
                    for (doc in querySnapshot) {
                        val pos = owner_ids.indexOf(doc.id)
                        if(pos != -1){
                            appointments[pos].owner_name = doc["name"] as String?
                            appointments[pos].phone = doc["phone"] as String?
                        }
                    }
                    appointments.sortBy{it.time}
                    data.value = appointments
                }.addOnFailureListener{
                    data.value = appointments
                }
            }

        }.addOnFailureListener{
            Log.d("ERROR", it.toString())
            data.value = null
        }
    }
}