package com.example.ambatik.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseListLikeArticle(

	@field:SerializedName("data")
	val data: List<DataItemLike?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class LikesItem(

	@field:SerializedName("status_like")
	val statusLike: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class DataItemLike(

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

	@field:SerializedName("likes")
	val likes: List<LikesItem?>? = null
) : Parcelable
