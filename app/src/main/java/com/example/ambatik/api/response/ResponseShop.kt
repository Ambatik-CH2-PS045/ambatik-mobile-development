package com.example.ambatik.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseShop(

	@field:SerializedName("data")
	val data: List<DataItemShop?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataItemShop(

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("url_product")
	val urlProduct: String? = null,

	@field:SerializedName("store_name")
	val storeName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("product_sold")
	val productSold: Int? = null
) : Parcelable
