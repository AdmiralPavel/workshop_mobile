package com.example.barbershop.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*
import kotlin.collections.ArrayList

data class Master(
    val name: String,
    val description: String,
    val rating: Int,
    val photo: String,
    val id: Int,
    val sessions: ArrayList<Session>,
    val reviews: ArrayList<Review>
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readArrayList(Date::class.java.classLoader) as ArrayList<Session>,
        parcel.readArrayList(Review::class.java.classLoader) as ArrayList<Review>
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(rating)
        parcel.writeString(photo)
        parcel.writeInt(id)
        parcel.writeList(sessions)
        parcel.writeList(reviews)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Master> {
        override fun createFromParcel(parcel: Parcel): Master {
            return Master(parcel)
        }

        override fun newArray(size: Int): Array<Master?> {
            return arrayOfNulls(size)
        }
    }
}