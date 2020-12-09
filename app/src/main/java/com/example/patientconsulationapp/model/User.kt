package com.example.patientconsulationapp.model

import android.os.Parcel
import android.os.Parcelable

data class User(
    val firstName: String = "",
    val email: String = "",
    val image: String = "",
    val dateOfBirth:String = ""
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
            source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(firstName)
        writeString(image)
        writeString(email)
        writeString(dateOfBirth)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}