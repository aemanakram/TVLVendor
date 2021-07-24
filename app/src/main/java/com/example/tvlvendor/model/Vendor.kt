package com.example.tvlvendor.model


import com.google.android.gms.maps.model.LatLng

data class Vendor (
        val id: String?,
        var address: String?,
        var location: LatLng?

)
/*fun setValues(name:String, _email:String, cnic:String, _phone:String,_uid:String){
        this.displayName = name
        this.email = _email
        this.cnicNumber = cnic
        this.phone = _phone
        this.uid = _uid
}*/