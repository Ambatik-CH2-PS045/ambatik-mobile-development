package com.example.ambatik.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseLikeArticle(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("liked")
	val like: Boolean,

	@field:SerializedName("message")
	val message: String
) : Parcelable
