package com.example.tvlvendor.viewmodel

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvlvendor.model.Part
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ViewPartsViewModel : ViewModel() {
    private lateinit var auth: FirebaseAuth
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var data = MutableLiveData<List<Part>>()

    fun loadParts(){
        auth = FirebaseAuth.getInstance()
        db.collection("Vendor").document(auth.currentUser?.uid!!).get().addOnSuccessListener { res ->
            if(res["inventory"] != null) {

                val parts = mutableListOf<Part>()
                Log.d("PART", "HERE")

                @Suppress("UNCHECKED_CAST")
                val partsIds = res["inventory"] as ArrayList<String?>
                Log.d("PART", partsIds[0].toString())
                for (part in partsIds){

                    if (part != null) {
                        db.collection("Part").document(part).get().addOnSuccessListener {
                            parts.add(Part(part, it["name"] as String?, it["description"] as String?, it["life"] as String?, it["type"] as String?,0,0))
                            data.value = parts
                            Log.d("PART", it["name"].toString())
                        }
                    }
                }
            }
        }.addOnFailureListener{
            Log.d("ERROR", it.toString())
            data.value = null
        }
    }

}