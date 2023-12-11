package com.example.ambatik.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseGetOrder(

	@field:SerializedName("availableOrder")
	val availableOrder: Boolean,

	@field:SerializedName("data")
	val data: List<DataItemOrder>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
) : Parcelable

@Parcelize
data class DataItemOrder(

	@field:SerializedName("product.url_product")
	val productUrlProduct: String,

	@field:SerializedName("total_item")
	val totalItem: Int,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("total_price")
	val totalPrice: Int,

	@field:SerializedName("product.id")
	val productId: Int,

	@field:SerializedName("product.name")
	val productName: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("other_item")
	val otherItem: Int
) : Parcelable
