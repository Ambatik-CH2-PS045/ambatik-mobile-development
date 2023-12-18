package com.example.ambatik.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseBatik(

	@field:SerializedName("data")
	val data: List<DataItemBatik?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataItemBatik(

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("url_batik")
	val urlBatik: String? = null
) : Parcelable
