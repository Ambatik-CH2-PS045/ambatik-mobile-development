package com.example.ambatik.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseDetailArticle(

	@field:SerializedName("data")
	val data: DataItemArticle,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("liked")
	val liked: Boolean,

	@field:SerializedName("message")
	val message: String
) : Parcelable

@Parcelize
data class DataItemArticle(

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
	val updatedAt: String,

	@SerializedName("likes")
	val likes: List<LikeItem>
) : Parcelable

@Parcelize
data class LikeItem(
	@field:SerializedName("status_like")
	val statusLike: String
) : Parcelable