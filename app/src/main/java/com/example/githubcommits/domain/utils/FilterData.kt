package com.example.githubcommits.domain.utils

import android.os.Parcel
import android.os.Parcelable

data class FilterData(
    val author: String? = null,
    val email: String? = null,
    val fromDate: Long? = null,
    val toDate: Long? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readLong()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(email)
        if (fromDate != null) {
            parcel.writeLong(fromDate)
        }
        if (toDate != null) {
            parcel.writeLong(toDate)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterData> {
        override fun createFromParcel(parcel: Parcel): FilterData {
            return FilterData(parcel)
        }

        override fun newArray(size: Int): Array<FilterData?> {
            return arrayOfNulls(size)
        }
    }
}
