package com.example.ambatik.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseGetOrder(

	@field:SerializedName("availableOrder")
	val availableOrder: Boolean? = null,

	@field:SerializedName("data")
	val data: List<DataItemOrder?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataItemOrder(

	@field:SerializedName("product.url_product")
	val productUrlProduct: String? = null,

	@field:SerializedName("total_item")
	val totalItem: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("total_price")
	val totalPrice: Int? = null,

	@field:SerializedName("product.id")
	val productId: Int? = null,

	@field:SerializedName("product.name")
	val productName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("other_item")
	val otherItem: Int? = null
) : Parcelable
