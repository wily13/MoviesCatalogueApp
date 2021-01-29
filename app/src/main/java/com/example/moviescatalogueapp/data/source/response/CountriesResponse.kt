package com.example.moviescatalogueapp.data.source.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountriesResponse(

	@field:SerializedName("iso_3166_1")
	val iso31661: String,

	@field:SerializedName("english_name")
	val englishName: String

): Parcelable

