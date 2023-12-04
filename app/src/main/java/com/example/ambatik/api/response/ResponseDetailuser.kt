package com.example.ambatik.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseDetailuser(

	@field:SerializedName("data")
	val data: DataDetailUser? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataDetailUser(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("url_profile")
	val urlProfile: String? = null,

	@field:SerializedName("point")
	val point: Int? = null,

	@field:SerializedName("username")
	val username: String? = null
) : Parcelable
