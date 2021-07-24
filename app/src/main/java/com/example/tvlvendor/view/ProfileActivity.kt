package com.example.tvlvendor.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.tvlvendor.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        if (savedInstanceState == null) {
            update()
        }
        update()

    }
    fun update(){
        val db = FirebaseFirestore.getInstance()
        val uid: String = FirebaseAuth.getInstance().currentUser!!.uid.toString()
        db.collection("Vendor")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        if(document.id == uid)
                        findViewById<EditText>(R.id.name).setText(document.data["name"].toString())
                        findViewById<EditText>(R.id.email).setText(document.data["email"].toString())
                        findViewById<EditText>(R.id.phoneNumber).setText(document.data["phone"].toString())
                        findViewById<EditText>(R.id.cnic).setText(document.data["cnic"].toString())

                    }
                } else {
                    Log.w("TAG", "Error getting documents.", task.exception)
                }
            }

        /*findViewById<EditText>(R.id.name).setText(Admin.displayName)
        findViewById<EditText>(R.id.email).setText(Admin.email())
        findViewById<EditText>(R.id.phoneNumber).setText(Admin.phone)
        findViewById<EditText>(R.id.cnic).setText(Admin.cnicNumber)*/


    }
    fun Back(view: View) {
        finish()
    }

    fun edit(view: View) {
        findViewById<TextView>(R.id.edit).visibility = View.INVISIBLE
        findViewById<Button>(R.id.logout).isClickable = false
        findViewById<Button>(R.id.save).isClickable = true
        findViewById<Button>(R.id.save).visibility = View.VISIBLE
        findViewById<Button>(R.id.logout).visibility = View.INVISIBLE
        findViewById<EditText>(R.id.name).isEnabled = true
        findViewById<EditText>(R.id.phoneNumber).isEnabled = true
        findViewById<EditText>(R.id.cnic).isEnabled = true
    }
    fun save(view: View){
        findViewById<TextView>(R.id.edit).visibility = View.VISIBLE
        findViewById<Button>(R.id.logout).isClickable = true
        findViewById<Button>(R.id.save).visibility = View.INVISIBLE
        findViewById<Button>(R.id.save).isClickable = false
        findViewById<Button>(R.id.logout).visibility = View.VISIBLE
        findViewById<EditText>(R.id.name).isEnabled = false
        findViewById<EditText>(R.id.phoneNumber).isEnabled = false
        findViewById<EditText>(R.id.cnic).isEnabled = false
        /*Admin.displayName = findViewById<EditText>(R.id.name).text.toString()
        Admin.cnicNumber = findViewById<EditText>(R.id.cnic).text.toString()
        Admin.phone = findViewById<EditText>(R.id.phoneNumber).text.toString()*/
        val uid: String = FirebaseAuth.getInstance().currentUser!!.uid
        val db = FirebaseFirestore.getInstance()
        var col = db.collection("Vendor").document(uid)
        col.update("name",findViewById<EditText>(R.id.name).text.toString())
        col.update("cnic",findViewById<EditText>(R.id.cnic).text.toString())
        col.update("phone",findViewById<EditText>(R.id.phoneNumber).text.toString())



    }

    fun Logout(view: View) {
        FirebaseAuth.getInstance().signOut()
        val value  = Intent(this, LoginActivity::class.java)
        value.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(value)

    }
}