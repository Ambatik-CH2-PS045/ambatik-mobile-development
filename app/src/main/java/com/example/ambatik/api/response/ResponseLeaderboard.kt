package com.example.ambatik.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseLeaderboard(

	@field:SerializedName("data")
	val data: List<DataItemLeaderboard?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataItemLeaderboard(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("point")
	val point: Int? = null,

	@field:SerializedName("url_profile")
	val urlProfile: String? = null
) : Parcelable
