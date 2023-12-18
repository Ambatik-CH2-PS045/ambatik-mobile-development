package com.example.ambatik.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseDetailBatik(

	@field:SerializedName("data")
	val data: DataDetailBatik? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataDetailBatik(

	@field:SerializedName("meaning")
	val meaning: String? = null,

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url_batik")
	val urlBatik: String? = null,

	@field:SerializedName("making_process")
	val makingProcess: String? = null,

	@field:SerializedName("usage_purpose")
	val usagePurpose: String? = null
) : Parcelable
