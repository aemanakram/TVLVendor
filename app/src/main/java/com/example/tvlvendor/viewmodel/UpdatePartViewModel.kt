package com.example.tvlvendor.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvlvendor.model.Part
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore


class UpdatePartViewModel: ViewModel() {
    private lateinit var auth: FirebaseAuth
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var data = MutableLiveData<List<Part>>()

    fun loadParts() {
        auth = FirebaseAuth.getInstance()
        db.collection("Vendor").document(auth.currentUser?.uid!!).get()
            .addOnSuccessListener { res ->
                if (res["inventory"] != null) {

                    val parts = mutableListOf<Part>()
                    val partsIds = mutableListOf<String>()

                    @Suppress("UNCHECKED_CAST")
                    val partsInfo = res["inventory"] as ArrayList<HashMap<String, Any>>?
                    if (partsInfo != null) {
                        for (part in partsInfo) {
                            partsIds.add(part["id"].toString())

                            parts.add(
                                Part(
                                    part["id"] as String?,
                                    null,
                                    null,
                                    null,
                                    null,
                                    quantity = part["quantity"] as Number?,
                                    price = part["price"] as Number?
                                )
                            )
                        }
                        val idRef = com.google.firebase.firestore.FieldPath.documentId()
                        db.collection("Part").whereIn(idRef, partsIds).get()
                            .addOnSuccessListener { partSnapshot ->
                                for (doc in partSnapshot) {
                                    val index = partsIds.indexOf(doc.id)
                                    if (index != -1)
                                        parts[index] = Part(
                                            doc.id,
                                            doc["name"] as String?,
                                            doc["description"] as String?,
                                            doc["life"] as String?,
                                            doc["type"] as String?,
                                            parts[index].quantity,
                                            parts[index].price
                                        )

                                }
                                data.value = parts
                            }.addOnFailureListener {
                                data.value = parts
                            }

                    }

                }
            }.addOnFailureListener {
                Log.d("ERROR", it.toString())
                data.value = null

            }

    }

    fun updatePart(part: Part, price: Int, quantity: Int) {
        val uid: String = FirebaseAuth.getInstance().currentUser!!.uid.toString()
        val id: String = part.id.toString()
        val addData = hashMapOf(
            "id" to id,
            "price" to price,
            "quantity" to quantity
        )

        val removeData = hashMapOf(
            "id" to part.id,
            "price" to part.price,
            "quantity" to part.quantity
        )
        db.collection("Vendor").document(uid).update("inventory",FieldValue.arrayRemove(removeData)).
        addOnSuccessListener {
            db.collection("Vendor").document(uid).update("inventory",FieldValue.arrayUnion(addData))
        }

    }
}


/* val addData = hashMapOf(
     "id" to id,
     "price" to price,
     "quantity" to quantity
 )
/* var users=" "
 val rootRef = FirebaseFirestore.getInstance()
 val applicationsRef = rootRef.collection("Vendor")
 val applicationIdRef = applicationsRef.document(uid)
 Log.d("ID",applicationIdRef.id.toString())
 applicationIdRef.get().addOnCompleteListener { task: Task<DocumentSnapshot> ->
     if (task.isSuccessful) {
         val document = task.result
         if (document.exists()) {
             val users =
                 document["inventory"] as List<Map<String, Any>>?
            // partsList=users;
         }
     }
 }*/

 // var docRef = db.collection("Vendor").document(uid).collection("inventory");

// delete the document
 //    docRef.document("lKjNIwEkjP537Ela6fhJ").delete();
 // loadParts()
 //  data.value.get(0).id
 // var index=0
 //val partsList = List<Map<String, Any>>()

 //  db.collection("Vendor").document(uid).update("inventory",FieldValue.arrayRemove(index))
 /*auth = FirebaseAuth.getInstance()
 db.collection("Vendor").document(uid).get().addOnSuccessListener { res ->
     if(res["inventory"] != null) {

         //val parts = mutableListOf<Part>()
         val partsIds = mutableListOf<String>()

         @Suppress("UNCHECKED_CAST")
         val partsInfo = res["inventory"] as ArrayList<HashMap<String, Any>>?
         if(partsInfo != null) {
             for (partindex in partsInfo) {
                 partsIds.add(partindex["id"].toString())

                 partsList.add(Part(partindex["id"] as String?,null,null,null,null,
                     quantity = partindex["quantity"] as Number?, price = partindex["price"] as Number?))
             }
             val idRef = com.google.firebase.firestore.FieldPath.documentId()
             db.collection("Part").whereIn(idRef, partsIds).get().addOnSuccessListener { partSnapshot ->
                 for (doc in partSnapshot){
                     val index = partsIds.indexOf(doc.id)
                     if(index != -1)
                         partsList[index] = Part(doc.id, doc["name"] as String?, doc["description"] as String?,
                             doc["life"] as String?, doc["type"] as String?, partsList[index].quantity,
                             partsList[index].price)

                 }
                // data.value = parts
             }.addOnFailureListener {
                // data.value = parts
             }
         }
     }
 }.addOnFailureListener{
     Log.d("ERROR", it.toString())
     data.value = null

 }*/
/* val docRef = db.collection("Vendor").document(uid)

val updates: MutableMap<String, Any> = HashMap()
 updates["inventory"] = FieldValue.delete()*/





/*      db.collection("Vendor").document(uid).update("inventory",
     FieldValue.arrayUnion(addData)).*/

 }*/




