package com.example.ambatik.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponsePredictBatik(

	@field:SerializedName("data")
	val data: DataPredictBatik? = null,

	@field:SerializedName("accuracy")
	val accuracy: Double? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("batikName")
	val batikName: String? = null
) : Parcelable

@Parcelize
data class DataPredictBatik(

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
