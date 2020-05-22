package com.example.midterm01mariamichakhvadze

import android.os.Parcel
import android.os.Parcelable

class CharacterModel {
    var results = ArrayList<Data>()

    class Data() : Parcelable {
        var id = 0;
        var name = ""
        var status = ""
        var species = ""
        var type = ""
        var gender = ""
        var image = ""

        constructor(parcel: Parcel) : this() {
            id = parcel.readInt()
            name = parcel.readString()!!
            status = parcel.readString()!!
            species = parcel.readString()!!
            type = parcel.readString()!!
            gender = parcel.readString()!!
            image = parcel.readString()!!
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(name)
            parcel.writeString(status)
            parcel.writeString(species)
            parcel.writeString(type)
            parcel.writeString(gender)
            parcel.writeString(image)
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun toString(): String {
            return "Data(id=$id, name='$name', status='$status', species='$species', type='$type', gender='$gender', image='$image')"
        }

        companion object CREATOR : Parcelable.Creator<Data> {
            override fun createFromParcel(parcel: Parcel): Data {
                return Data(parcel)
            }

            override fun newArray(size: Int): Array<Data?> {
                return arrayOfNulls(size)
            }
        }


    }
}