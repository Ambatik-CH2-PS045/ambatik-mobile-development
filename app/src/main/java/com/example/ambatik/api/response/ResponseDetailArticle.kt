package com.example.ambatik.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseDetailArticle(

	@field:SerializedName("data")
	val data: DataItemArticle? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("liked")
	val liked: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataItemArticle(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("url_banner")
	val urlBanner: String? = null,

	@field:SerializedName("total_like")
	val totalLike: Int? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@SerializedName("likes")
	val likes: List<LikeItem?>? = null
) : Parcelable

@Parcelize
data class LikeItem(
	@field:SerializedName("status_like")
	val statusLike: String? = null
) : Parcelable