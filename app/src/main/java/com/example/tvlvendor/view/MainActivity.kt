package com.example.tvlvendor.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.tvlvendor.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // val db = FirebaseFirestore.getInstance()
        /*val curr=FirebaseAuth.getInstance().currentUser
        if (curr != null) {
            Log.d("name", curr.displayName.toString())
        }
        if (curr != null) {
            findViewById<TextView>(R.id.username).text= "ALi"
            findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE

        }*/
        val db = FirebaseFirestore.getInstance()
        val uid: String = FirebaseAuth.getInstance().currentUser!!.uid.toString()
        db.collection("Vendor")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        if(document.id == uid)

                        findViewById<TextView>(R.id.username).text= document.data["name"].toString()
                        findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
                    }
                } else {
                    Log.w("TAG", "Error getting documents.", task.exception)
                }
            }

        val viewPartsButton = findViewById<View>(R.id.ViewPartsButton)
        val viewAppointmentsButton = findViewById<View>(R.id.ViewAppointmentsButton)
        val approveAppointmentsButton = findViewById<View>(R.id.ApproveAppointmentsButton)
        val updateLocationButton = findViewById<View>(R.id.UpdateLocationButton)
        val addPartButton =findViewById<View>(R.id.AddPartsButton)
        val updatePartButton=findViewById<View>(R.id.UpdatePartsButton)
        val profileButton=findViewById<View>(R.id.profileButton)
        profileButton.setOnClickListener {
                val value  = Intent(this, ProfileActivity::class.java)
                startActivity(value)
        }

        viewPartsButton.setOnClickListener {
            val value  = Intent(this, ViewPartsActivity::class.java)
            startActivity(value)
        }
        viewAppointmentsButton.setOnClickListener {
            val value  = Intent(this, ViewAppointmentsActivity::class.java)
            startActivity(value)
        }
        approveAppointmentsButton.setOnClickListener {
            val value  = Intent(this, ApproveAppointmentsActivity::class.java)
            startActivity(value)
        }
        updateLocationButton.setOnClickListener {
            val value  = Intent(this, UpdateMapsActivity::class.java)
            startActivity(value)
        }
        addPartButton.setOnClickListener {
            startActivity(Intent(this,AddPartActivity::class.java))
        }
        updatePartButton.setOnClickListener {
            startActivity(Intent(this,UpdatePartActivity::class.java))
        }


    }
    /*override fun onResume() {
        super.onResume()
        val curr=FirebaseAuth.getInstance().currentUser
        if (curr != null) {
            findViewById<TextView>(R.id.username).text= curr.displayName
            //findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE

        }

    }*/
}