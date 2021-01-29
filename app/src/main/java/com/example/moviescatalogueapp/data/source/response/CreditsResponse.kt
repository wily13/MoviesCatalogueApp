package com.example.moviescatalogueapp.data.source.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreditsResponse(
	val crew: List<CrewItem>
): Parcelable

@Parcelize
data class CrewItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("job")
	val job: String
): Parcelable

