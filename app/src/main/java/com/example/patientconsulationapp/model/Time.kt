package com.example.patientconsulationapp.model

import android.os.Parcel
import android.os.Parcelable

class Time(val time:ArrayList<String> = ArrayList()):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList()!!,
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Time> {
        override fun createFromParcel(parcel: Parcel): Time {
            return Time(parcel)
        }

        override fun newArray(size: Int): Array<Time?> {
            return arrayOfNulls(size)
        }
    }
}
