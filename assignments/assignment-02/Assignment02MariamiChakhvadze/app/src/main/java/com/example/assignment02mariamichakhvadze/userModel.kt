package com.example.assignment02mariamichakhvadze

import android.os.Parcel
import android.os.Parcelable

class userModel(var fullName:String, var email:String, val birthday:String, val gender:String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fullName)
        parcel.writeString(email)
        parcel.writeString(birthday)
        parcel.writeString(gender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<userModel> {
        override fun createFromParcel(parcel: Parcel): userModel {
            return userModel(parcel)
        }

        override fun newArray(size: Int): Array<userModel?> {
            return arrayOfNulls(size)
        }
    }

}