package com.example.patientconsulationapp.model

import android.os.Parcel
import android.os.Parcelable

class Booking(val firstNam:String = "",val date:String = "",val time:String = "",val phoneNumber:String =""):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstNam)
        parcel.writeString(date)
        parcel.writeString(time)
        parcel.writeString(phoneNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Booking> {
        override fun createFromParcel(parcel: Parcel): Booking {
            return Booking(parcel)
        }

        override fun newArray(size: Int): Array<Booking?> {
            return arrayOfNulls(size)
        }
    }
}