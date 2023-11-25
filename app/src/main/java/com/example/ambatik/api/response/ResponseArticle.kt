package com.example.ambatik.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ResponseArticle(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

@Parcelize
data class DataItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("url_banner")
	val urlBanner: String,

	@field:SerializedName("total_like")
	val totalLike: Int,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
) : Parcelable
